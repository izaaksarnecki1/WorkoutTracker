package no.uib.inf101.db;

import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseController {
  private static final String DB_PATH = "jdbc:sqlite:src/main/resources/db/workout-tracker.db";
  private Connection connection;
  private final String[] tables = {"users", "workouts", "exercise"};

  public DatabaseController() {
    this.dropTables();
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
      e.printStackTrace();
    }
  }

  private void close() {
    try {
      if (this.connection != null) {
        this.connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
      e.printStackTrace();
    }
  }

  private static String createTableSQLString(String tableName) {
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

  private void addRow(DbUploadable entity) {
    
  }

  private String createAddRowString(DbUploadable entity) {
    String tableName = entity.getTableName();
    ArrayList<String> attributeNames = entity.getAttributeNames();
    HashMap<String, Object> entityAttributes = entity.getUploadableData();

    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO ").append(tableName).append("(");

    for (int i = 0; i < attributeNames.size(); i++) {
      sb.append(attributeNames.get(i));
      if (i < attributeNames.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append(") VALUES(");

    for (int i = 0; i < attributeNames.size(); i++) {
      sb.append("?");
      if (i < attributeNames.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append(")");
    return sb.toString();
  }

  public void addUser(User user) {
    HashMap<String, Object> userData = user.getUploadableData();

    String sqlString = "INSERT INTO users(username, password) VALUES(?,?)";

    try {
      this.connect();
      PreparedStatement pStatement = this.connection.prepareStatement(sqlString);

      for (int i = 0; i < userData.size(); i++) {
        pStatement.setString(i + 1, userData.get(i).toString());
      }
      pStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private String createRowSQLString(String tableName) {
    return switch (tableName) {
      case "users" -> "INSERT INTO users(username, password) VALUES(?,?)";
      case "workouts" -> "INSERT INTO workouts(date) VALUES(?,?)";
      case "exercise" -> "INSERT INTO exercise(ex_name, sets, reps, weight) VALUES(?,?,?,?)";
      default -> throw new IllegalStateException("SQL Row creation failed for value: " + tableName);
    };
  }

  private void setupForeignKey() {
    String sqlString = "PRAGMA foreign_keys = ON;";
    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      statement.execute(sqlString);
      System.out.println("Successfully enabled Foreign Keys. ");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void dropTables() {
    String sqlString = "SELECT 'DROP TABLE IF EXISTS ' || name || ';' \n"
            + "FROM sqlite_master \n"
            + "WHERE type = 'table';";
    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      ResultSet res = statement.executeQuery(sqlString);
      while (res.next()) {
        String dropTableStatement = res.getString(1);
        statement.execute(dropTableStatement);
        System.out.println("Dropped table: " + dropTableStatement);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
