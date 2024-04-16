package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseController {

  private static final String DB_PATH = detectOs();
  private final String[] tables = {"users", "workouts", "exercise"};

  public DatabaseController() {
//    this.dropTables();
    this.setupForeignKey();
    this.setupTables();
  }

  public static void addRow(DbUploadable entity) {
    SQLQueryCreator creator = new SQLQueryCreator(entity);
    String sqlStirng = creator.createAddRowString();
    HashMap<String, Object> uploadAbleData = entity.getUploadableData();
    ArrayList<String> attributeNames = entity.getAttributeNames();

    if (!validateParamsForString(uploadAbleData, attributeNames)) {
      return;
    }

    try (Connection connection = connect();
         PreparedStatement pStatement = connection.prepareStatement(sqlStirng)) {
      int idx = 1;
      for (String attribute : attributeNames) {
        pStatement.setString(idx++, uploadAbleData.get(attribute).toString());
      }
      pStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  protected static boolean validatePass(String username, String password) {
    String sqlString = "SELECT password FROM users WHERE username = '" + username + "';" ;

    try (Connection connection = connect();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlString)) {
      String s = resultSet.getString("password");
      return s.equals(password);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return false;
  }
  static String fetchUserId(String username) {
    String sqlString = "SELECT id FROM users WHERE username = '" + username + "';";

    try (Connection connection = connect();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sqlString)) {
      String s = String.valueOf(resultSet.getInt("id"));
      System.out.println(s.getClass());
      return s;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  private void setupTables() {
    for (String tableName : this.tables) {
      initiateTables(tableName);
    }
  }

  private void setupForeignKey() {
    String sqlString = "PRAGMA foreign_keys = ON;";
    try (Connection connection = connect();
         Statement statement = connection.createStatement()) {
      statement.execute(sqlString);
      System.out.println("Successfully enabled Foreign Keys. ");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private static Connection connect() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(DB_PATH);
      return connection;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return connection;
  }

  private static void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private void initiateTables(String tableName) {
    String sqlString = SQLQueryCreator.getTableSQLString(tableName);

    try (Connection connection = connect();
         Statement statement = connection.createStatement()) {
      statement.execute(sqlString);
      closeConnection(connection);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private void dropTables() {
    for (String tableName : this.tables) {
      dropTable(tableName);
    }
    System.out.println("Successfully dropped all tables. ");
  }

  private void dropTable(String tableName) {
    String sqlString = "DROP TABLE IF EXISTS " + tableName + ";";
    try (Connection connection = connect();
         Statement statement = connection.createStatement()) {
      statement.execute(sqlString);
      System.out.println("Dropped table: " + tableName);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private static boolean validateParamsForString(
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

  private static String detectOs() {
    String os = System.getProperty("os.name");
    if (os.toLowerCase().equals("windows 11")) {
      return "jdbc:sqlite:src/main/resources/db/workout-tracker.db";
    }
    return null;
  }
}
