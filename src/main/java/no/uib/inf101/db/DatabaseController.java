package no.uib.inf101.db;

import no.uib.inf101.model.user.User;

import java.sql.*;
import java.util.List;

public class DatabaseController<E> {
  private static final String DB_PATH = "jdbc:sqlite:src/main/resources/database/workout-tracker.db";
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
      createTable(tableName);
    }
  }

  private void createTable(String tableName) {
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
      default -> throw new IllegalStateException("SQL Table creation failed with value: " + tableName);
    };
  }

  private void addRow(String tableName, User user) {
    String sqlString = createRowSQLString(tableName);
    try {
      this.connect();
      PreparedStatement statement = this.connection.prepareStatement(sqlString);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addUser(User user) {
    String sqlString = "INSERT INTO users(username, password) VALUES(?,?)";
    List<Object> userData = user.getUploadableData();

    try {
      this.connect();
      PreparedStatement pStatement = this.connection.prepareStatement(sqlString);

      for (int i = 0; i < userData.size(); i++) {
        pStatement.setString(i+1, userData.get(i).toString());
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
      default -> throw new IllegalStateException("SQL Row creation failed with value: " + tableName);
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