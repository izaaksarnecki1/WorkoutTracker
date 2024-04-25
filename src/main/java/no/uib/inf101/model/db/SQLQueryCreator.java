package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.User;
import no.uib.inf101.model.Workout;
import no.uib.inf101.model.Exercise;


import java.util.ArrayList;

public class SQLQueryCreator {

  private DbUploadable uploadable;

  public SQLQueryCreator(DbUploadable uploadable) {
    this.uploadable = uploadable;
  }

  protected String createAddRowString() {
    String tableName = this.uploadable.getTableName();
    ArrayList<String> attributeNames = this.uploadable.getAttributeNames();

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

  protected static String getTableSQLString(String tableName) {
    return switch (tableName) {
      case User.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
        + "	id integer PRIMARY KEY,\n"
        + "	" + User.USERNAME + " text NOT NULL UNIQUE,\n"
        + "	" + User.PASSWORD + " text NOT NULL,\n"
        + "	" + User.FIRST_NAME + " text,\n"
        + "	" + User.LAST_NAME + " text,\n"
        + "	" + User.WEIGHT + " int,\n"
        + "	" + User.HEIGHT + " int\n"
        + ");";
      case Workout.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
        + "	id integer PRIMARY KEY,\n"
        + " " + Workout.USERID + " integer,\n"
        + " " + Workout.WORKOUTNAME + " text,\n"
        + " " + Workout.WORKOUTDATE + " DATE,\n"
        + " FOREIGN KEY (" + Workout.USERID + ") REFERENCES " + User.TABLE_NAME + "(id)"
        + ");";
      case Exercise.TABLE_NAME -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
        + "	id integer PRIMARY KEY,\n"
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

  protected static String getRowSQLString(DbUploadable entity) {
    String tablename = entity.getTableName();
    int id = entity.getId();

    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(tablename).append(" WHERE id = ").append(id);
    return sb.toString();
  }

  protected static String getLastIdSQLString() {
    return "SELECT last_insert_rowid()";
  }
}
