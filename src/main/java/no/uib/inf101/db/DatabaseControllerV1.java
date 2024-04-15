package no.uib.inf101.db;

import no.uib.inf101.model.DbUploadable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseControllerV1 {
  private static final String DB_PATH = "jdbc:sqlite:src/main/resources/db/workout-tracker.db";
  private Connection connection;
  private final String[] tables = {"users", "workouts", "exercise"};

  public DatabaseControllerV1() {
//    this.dropTables();
    this.setupForeignKey();
    this.setupTables();
  }

  // https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
  private void connect() {
    this.connection = null;
    try {
      this.connection = DriverManager.getConnection(DB_PATH);
//      System.out.println("Successful connection");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void close() {
    try {
      if (this.connection != null) {
        this.connection.close();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void setupTables() {
    for (String tableName : this.tables) {
      initiateTables(tableName);
    }
  }

  private void initiateTables(String tableName) {
    String sqlString = createTableSQLString(tableName);
    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      statement.execute(sqlString);
      this.close();
    } catch (SQLException e) {
      // Crazy error handling
      System.out.println(e.getMessage());
    }
  }

  private static String createTableSQLString(String tableName) {
    // TODO: refac into sqlquerycreator, where attribute class is checked to determine type in query
    // refac idea might cause problem as primary keys etc have to be predetermined?

    return switch (tableName) {
      case "users" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + "	username text NOT NULL UNIQUE,\n"
              + "	password text NOT NULL\n"
              + ");";
      case "workouts" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + " user_id integer, \n"
              + "	date text,\n"
              + " FOREIGN KEY (user_id) REFERENCES users (id)"
              + ");";
      case "exercise" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + " workout_id integer, \n"
              + "	ex_name text NOT NULL,\n"
              + "	sets integer NOT NULL,\n"
              + "	reps integer NOT NULL,\n"
              + "	weight text,\n"
              + " FOREIGN KEY (workout_id) REFERENCES workouts (id)"
              + ");";
      default -> throw new IllegalStateException("SQL Table creation failed for value: " + tableName);
    };
  }

  public void addRow(DbUploadable entity) {
    SQLQueryCreator creator = new SQLQueryCreator(entity);
    String sqlStirng = creator.createAddRowString();
    HashMap<String, Object> uploadAbleData = entity.getUploadableData();
    ArrayList<String> attributeNames = entity.getAttributeNames();

    if (!validateParamsForString(uploadAbleData, attributeNames)) {
      return;
    }

    try {
      this.connect();
      PreparedStatement pStatement = this.connection.prepareStatement(sqlStirng);

      int idx = 1;
      for (String attribute : attributeNames) {
        pStatement.setString(idx++, uploadAbleData.get(attribute).toString());
      }
      pStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void setupForeignKey() {
    String sqlString = "PRAGMA foreign_keys = ON;";
    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      statement.execute(sqlString);
      System.out.println("Successfully enabled Foreign Keys. ");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void dropTable(String tableName) {
    String sqlString = "DROP TABLE IF EXISTS " + tableName + ";";
    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      statement.execute(sqlString);
      System.out.println("Dropped table: " + tableName);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void dropTables() {
    for (String tableName : this.tables) {
      dropTable(tableName);
    }
    System.out.println("Successfully dropped all tables. ");
  }

  private boolean validateParamsForString(
          HashMap<String, Object> uploadAbleData, ArrayList<String> attributeNames
  ) {
    if (attributeNames.size() != uploadAbleData.keySet().size()) {
      System.err.println("Attribute list size does not match data keys size. ");
      return false;
    }

    if (attributeNames.isEmpty()) {
      System.err.println("Attribute list is empty. ");
      return false;
    }

    return true;
  }

  /**
   * Methods that fetches a user_id by using the username. Used by
   * Authenticator to safely find if a user exists in db, and to login.
   * If no user has the username,
   *
   * @param username String of User's username
   * @return String of user_id
   */
  public String fetchUserId(String username) {
    String sqlString = "SELECT id FROM users WHERE username = '" + username + "';";

    try {
      this.connect();
      Statement stmt = this.connection.createStatement();
      ResultSet resultSet = stmt.executeQuery(sqlString);
      while (resultSet.next()) {
        System.out.println(resultSet.getInt("id"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


  /**
   * Method that takes in a DbUploadable object and if said object
   * holds a foreign key, it will return .
   * @param entity
   * @return
   */
  public String fetchParentId(DbUploadable entity) {
    return null;
  }


}
