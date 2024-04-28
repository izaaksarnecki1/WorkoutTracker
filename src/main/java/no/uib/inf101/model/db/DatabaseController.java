package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.Exercise;
import no.uib.inf101.model.User;
import no.uib.inf101.model.Workout;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.TestOnly;

/**
 * The DatabaseController class is responsible for managing the database
 * operations
 * for the workout tracker application. It provides methods for adding,
 * updating, and
 * retrieving data from the database.
 * 
 * The construction of this class was inspired by the following tutorial:
 * https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
 */
public class DatabaseController {

  private String dbPath = "jdbc:sqlite:src/main/resources/db/workout-tracker.db";
  private final String[] tables = { User.TABLE_NAME, Workout.TABLE_NAME, Exercise.TABLE_NAME };

  /**
   * Constructs a new DatabaseController object with the specified database path.
   * 
   * @param dbPath the path to the database file
   */
  public DatabaseController(String dbPath) {
    this.dbPath = dbPath;
    setupDb(false);
  }

  /**
   * Constructs a new DatabaseController object with the default database path.
   */
  public DatabaseController() {
    setupDb(false);
  }

  /**
   * Sets up the database by dropping tables (if specified), setting up foreign
   * keys, and creating tables.
   * 
   * @param dropTables true if the tables should be dropped before setting up,
   *                   false otherwise
   */
  void setupDb(boolean dropTables) {
    if (dropTables) {
      this.dropTables();
    }
    this.setupForeignKey();
    this.setupTables();
  }

  /**
   * Inserts a row into the database for the specified entity.
   * 
   * @param entity the entity to be inserted into the database
   */
  public void insertRow(DbUploadable entity) {
    if (!validateEntity(entity)) {
      return;
    }
    String sqlString = SQLQueryCreator.createAddRowString(entity);
    HashMap<String, Object> uploadAbleData = entity.getUploadableData();
    ArrayList<String> attributeNames = entity.getAttributeNames();

    if (!validateParamsForString(uploadAbleData, attributeNames)) {
      return;
    }

    try (Connection connection = connect();
        PreparedStatement pStatement = connection.prepareStatement(sqlString)) {
      int idx = 1;
      for (String attribute : attributeNames) {
        pStatement.setString(idx++, uploadAbleData.get(attribute).toString());
      }
      pStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Retrieves a row from the database for the specified entity.
   * 
   * @param entity the entity to retrieve from the database
   * @return a map containing the attribute names and values of the retrieved row
   */
  public Map<String, String> getRow(DbUploadable entity) {
    if (!validateEntity(entity)) {
      return null;
    }
    String sqlString = SQLQueryCreator.getRowSQLString(entity);
    ArrayList<String> attributeNames = entity.getAttributeNames();
    Map<String, String> dbAttributes = new HashMap<>();

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      while (resultSet.next()) {
        for (String attribute : attributeNames) {
          dbAttributes.put(attribute, resultSet.getObject(attribute).toString());
        }
      }
      return dbAttributes;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  /**
   * Updates a row in the database for the specified entity.
   * 
   * @param entity the entity to be updated in the database
   */
  public void updateRow(DbUploadable entity) {
    if (!validateEntity(entity)) {
      return;
    }
    String sqlString = SQLQueryCreator.updateRowSQLString(entity);
    HashMap<String, Object> uploadAbleData = entity.getUploadableData();
    ArrayList<String> attributeNames = entity.getAttributeNames();

    if (!validateParamsForString(uploadAbleData, attributeNames)) {
      return;
    }

    try (Connection connection = connect();
        PreparedStatement pStatement = connection.prepareStatement(sqlString)) {
      int idx = 1;
      for (String attribute : attributeNames) {
        pStatement.setObject(idx++, uploadAbleData.get(attribute));
      }
      pStatement.setInt(idx, ((User) entity).getId());
      pStatement.executeUpdate();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Retrieves the last inserted row ID for the specified entity.
   * 
   * @param entity the entity to retrieve the last inserted row ID for
   * @return the last inserted row ID as a string
   */
  public String getLastId(DbUploadable entity) {
    String sqlString = SQLQueryCreator.getLastIdSQLString(entity);

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {

      if (resultSet != null && resultSet.next()) {
        String lastId = resultSet.getString(1);
        return lastId;
      }
    } catch (SQLException e) {
      System.err.println("Error retrieving last inserted row ID: " + e.getMessage());
    }
    return null;
  }

  /**
   * Retrieves the workouts for the specified user from the database.
   * 
   * @param entity the user entity to retrieve the workouts for
   * @return a list of lists containing the workout details
   */
  public ArrayList<ArrayList<String>> getUserWorkouts(DbUploadable entity) {
    if (!validateEntity(entity)) {
      return null;
    }
    String sqlString = SQLQueryCreator.getUserWorkouts(entity);
    ArrayList<ArrayList<String>> workouts = new ArrayList<>();

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      while (resultSet.next()) {
        ArrayList<String> workout = new ArrayList<>();
        workout.add(resultSet.getString("id"));
        workout.add(resultSet.getString(Workout.WORKOUTNAME));
        workout.add(resultSet.getString(Workout.WORKOUTDATE));
        workouts.add(workout);
      }
      return workouts;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  /**
   * Retrieves the exercises for the specified workout from the database.
   * 
   * @param workoutId the ID of the workout to retrieve the exercises for
   * @return a list of lists containing the exercise details
   */
  public ArrayList<ArrayList<String>> getWorkoutExercises(int workoutId) {
    if (workoutId < 1) {
      return null;
    }
    String sqlString = SQLQueryCreator.getWorkoutExercises(workoutId);
    ArrayList<ArrayList<String>> exercises = new ArrayList<>();

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      while (resultSet.next()) {
        ArrayList<String> exercise = new ArrayList<>();
        exercise.add(resultSet.getString("id"));
        exercise.add(resultSet.getString(Exercise.EXERCISE_NAME));
        exercise.add(resultSet.getString(Exercise.REPS));
        exercise.add(resultSet.getString(Exercise.SETS));
        exercise.add(resultSet.getString(Exercise.WEIGHT));
        exercises.add(exercise);
      }
      return exercises;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  /**
   * Retrieves a user from the database by username.
   * This method is protected and used for testing purposes.
   * 
   * @param username the username of the user to be retrieved
   * @return a User object representing the retrieved user
   */
  @TestOnly
  protected User getUserByUsername(String username) {
    if (username == null) {
      return null;
    }
    String sqlString = SQLQueryCreator.getUserByUsernameSQL(username);

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      User user = new User(resultSet.getString("username"), resultSet.getString("password"));
      user.setId(resultSet.getInt("id"));
      user.setFirstName(resultSet.getString("first_name"));
      user.setLastName(resultSet.getString("last_name"));
      user.setWeight(resultSet.getInt("weight"));
      user.setHeight(resultSet.getInt("height"));
      return user;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  /**
   * Validates the password for the specified username.
   * 
   * @param username the username to validate the password for
   * @param password the password to validate
   * @return true if the password is valid, false otherwise
   */
  protected boolean validatePass(String username, String password) {
    String sqlString = SQLQueryCreator.validatePassSQL(username);

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      String s = resultSet.getString("password");
      if (s == null) {
        return false;
      }
      return s.equals(password);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  /**
   * Fetches the user ID for the specified username from the database.
   * 
   * @param username the username to fetch the user ID for
   * @return the user ID as a string
   */
  protected String fetchUserId(String username) {
    String sqlString = SQLQueryCreator.fetchUserIdSQL(username);

    try (Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString)) {
      return String.valueOf(resultSet.getInt("id"));
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  void dropTables() {
    for (String tableName : this.tables) {
      dropTable(tableName);
    }
    System.out.println("Successfully dropped all tables. ");
  }

  boolean tableExists(String tableName) {
    try (Connection connection = connect()) {
      DatabaseMetaData metaData = connection.getMetaData();
      try {

        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        return resultSet.next();
      } catch (SQLException e) {
        System.err.println("Error checking if table exists: " + e.getMessage());
        return false;
      }
    } catch (SQLException e) {
      System.err.println("Error connecting to database: " + e.getMessage());
      return false;
    }
  }

  private void setupTables() {
    for (String tableName : this.tables) {
      initiateTables(tableName);
    }
  }

  private void setupForeignKey() {
    String sqlString = SQLQueryCreator.enablePragma();
    try (Connection connection = connect();
        Statement statement = connection.createStatement()) {
      statement.execute(sqlString);
      System.out.println("Successfully enabled Foreign Keys. ");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private Connection connect() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(this.dbPath);
      return connection;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return connection;
  }

  private void closeConnection(Connection connection) {
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

  private void dropTable(String tableName) {
    String sqlString = SQLQueryCreator.dropTableSQL(tableName);
    try (Connection connection = connect();
        Statement statement = connection.createStatement()) {
      statement.execute(sqlString);
      System.out.println("Dropped table: " + tableName);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  private boolean validateParamsForString(
      HashMap<String, Object> uploadAbleData, ArrayList<String> attributeNames) {
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

  private boolean validateEntity(DbUploadable entity) {
    if (entity.getUploadableData().isEmpty() || entity == null) {
      return false;
    } else {
      return true;
    }
  }
}
