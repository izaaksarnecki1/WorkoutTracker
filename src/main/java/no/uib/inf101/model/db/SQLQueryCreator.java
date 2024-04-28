package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.User;
import no.uib.inf101.model.Workout;
import no.uib.inf101.model.Exercise;

import java.util.ArrayList;

/**
 * The SQLQueryCreator class provides static methods to create SQL queries for
 * various operations.
 */
public class SQLQueryCreator {

  /**
   * Creates an SQL query string for adding a row to a database table.
   *
   * @param entity The DbUploadable entity representing the row to be added
   * @return The SQL query string for adding the row
   */
  protected static String createAddRowString(DbUploadable entity) {
    String tablename = entity.getTableName();
    ArrayList<String> attributes = entity.getAttributeNames();

    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO ").append(tablename).append("(");

    for (int i = 0; i < attributes.size(); i++) {
      sb.append(attributes.get(i));
      if (i < attributes.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append(") VALUES(");

    for (int i = 0; i < attributes.size(); i++) {
      sb.append("?");
      if (i < attributes.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * Creates an SQL query string for updating a row in a database table.
   *
   * @param entity The DbUploadable entity representing the row to be updated
   * @return The SQL query string for updating the row
   */
  protected static String updateRowSQLString(DbUploadable entity) {
    String tablename = entity.getTableName();
    ArrayList<String> attributes = entity.getAttributeNames();

    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE ").append(tablename).append(" SET ");

    for (int i = 0; i < attributes.size(); i++) {
      sb.append(attributes.get(i)).append(" = ?");
      if (i < attributes.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append(" WHERE id = ?");
    return sb.toString();
  }

  /**
   * Creates an SQL query string for creating a database table.
   *
   * @param tableName The name of the table to be created
   * @return The SQL query string for creating the table
   */
  protected static String getTableSQLString(String tableName) {
    return switch (tableName) {
      case User.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
          + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
          + "	" + User.USERNAME + " text NOT NULL UNIQUE,\n"
          + "	" + User.PASSWORD + " text NOT NULL,\n"
          + "	" + User.FIRST_NAME + " text,\n"
          + "	" + User.LAST_NAME + " text,\n"
          + "	" + User.WEIGHT + " int,\n"
          + "	" + User.HEIGHT + " int\n"
          + ");";
      case Workout.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
          + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
          + " " + Workout.USERID + " integer,\n"
          + " " + Workout.WORKOUTNAME + " text,\n"
          + " " + Workout.WORKOUTDATE + " DATE,\n"
          + " FOREIGN KEY (" + Workout.USERID + ") REFERENCES " + User.TABLE_NAME + "(id)"
          + ");";
      case Exercise.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
          + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
          + " " + Exercise.WORKOUT_ID + " integer,\n"
          + " " + Exercise.EXERCISE_NAME + " text NOT NULL,\n"
          + "	" + Exercise.SETS + " integer NOT NULL,\n"
          + "	" + Exercise.REPS + " integer NOT NULL,\n"
          + "	" + Exercise.WEIGHT + " text,\n"
          + " FOREIGN KEY (" + Exercise.WORKOUT_ID + ") REFERENCES " + Workout.TABLE_NAME + "(id)"
          + ");";
      default -> throw new IllegalStateException("SQL Table creation failed for value: " + tableName);
    };
  }

  /**
   * Creates an SQL query string for retrieving a row from the database table.
   *
   * @param entity The DbUploadable entity representing the row to be retrieved
   * @return The SQL query string for retrieving the row
   */
  protected static String getRowSQLString(DbUploadable entity) {
    String tablename = entity.getTableName();
    int id = entity.getId();

    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(tablename).append(" WHERE id = ").append(id);
    return sb.toString();
  }

  /**
   * Creates an SQL query string for retrieving the last inserted ID from the
   * database table.
   *
   * @param entity The DbUploadable entity representing the table
   * @return The SQL query string for retrieving the last inserted ID
   */
  protected static String getLastIdSQLString(DbUploadable entity) {
    String tablename = entity.getTableName();

    StringBuilder sb = new StringBuilder();
    sb.append("SELECT seq FROM sqlite_sequence WHERE name=").append('"').append(tablename).append('"');
    return sb.toString();
  }

  /**
   * Creates an SQL query string for retrieving all workouts of a user from the
   * database.
   *
   * @param entity The DbUploadable entity representing the user.
   * @return The SQL query string for retrieving the user's workouts.
   */
  protected static String getUserWorkouts(DbUploadable entity) {
    int id = entity.getId();
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(Workout.TABLE_NAME).append(" WHERE ").append(Workout.USERID).append(" = ")
        .append(id)
        .append(" ORDER BY date DESC");
    return sb.toString();
  }

  /**
   * Creates an SQL query string for retrieving all exercises of a workout from
   * the database.
   *
   * @param id The ID of the workout
   * @return The SQL query string for retrieving the workout's exercises
   */
  protected static String getWorkoutExercises(int id) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(Exercise.TABLE_NAME).append(" WHERE ").append(Exercise.WORKOUT_ID).append(" = ")
        .append(id);
    return sb.toString();
  }

  protected static String getUserByUsernameSQL(String username) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(User.TABLE_NAME).append(" WHERE ").append(User.USERNAME).append(" = ")
        .append('"').append(username).append('"');
    return sb.toString();
  }

  protected static String validatePassSQL(String username) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT password FROM ").append(User.TABLE_NAME).append(" WHERE ").append(User.USERNAME).append(" = ")
        .append('"').append(username).append('"');
    return sb.toString();
  }

  protected static String fetchUserIdSQL(String username) {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT id FROM ").append(User.TABLE_NAME).append(" WHERE ").append(User.USERNAME).append(" = ")
        .append('"').append(username).append('"');
    return sb.toString();
  }

  protected static String enablePragma() {
    return "PRAGMA foreign_keys = ON;";
  }

  protected static String dropTableSQL(String tableName) {
    return "DROP TABLE IF EXISTS " + tableName + ";";
  }
}
