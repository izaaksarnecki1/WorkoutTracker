package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;

import java.util.ArrayList;

public class SQLQueryCreator {

  private DbUploadable uploadable;

  public SQLQueryCreator(DbUploadable uploadable) {
    this.uploadable = uploadable;
  }

  protected void setUploadable(DbUploadable uploadable) {
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

  public static String getTableSQLString(String tableName) {
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
}
