package no.uib.inf101.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
  private static final String DB_PATH = "jdbc:sqlite:src/main/resources/database/workout-tracker.db";
  private Connection connection;
  private final String[] tables = {"users", "workouts", "exercise"};

  public DatabaseController() {
    this.setupTables();
  }

  // https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
  private void connect() {
    this.connection = null;
    try {
      this.connection = DriverManager.getConnection(DB_PATH);
      System.out.println("Successful connection");
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
    String sql = createTableSQLString(tableName);

    try {
      this.connect();
      Statement statement = this.connection.createStatement();
      statement.execute(sql);
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
              + "	username text NOT NULL,\n"
              + "	password text NOT NULL\n"
              + ");";
      case "workouts" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + "	date text,\n"
              + "	exercise text NOT NULL\n"
              + ");";
      case "exercise" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + "	description text NOT NULL,\n"
              + "	sets integer NOT NULL,\n"
              + "	reps text NOT NULL,\n"
              + "	weight text\n"
              + ");";
      default -> throw new IllegalStateException("SQL Table creation failed with value: " + tableName);
    };
  }
}
