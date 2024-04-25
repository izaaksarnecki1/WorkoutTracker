package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;

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
      case "users" -> "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
              + "	id integer PRIMARY KEY,\n"
              + "	username text NOT NULL UNIQUE,\n"
              + "	password text NOT NULL,\n"
              + " first_name text,\n"
              + " last_name text,\n"
              + " weight int,\n"
              + " height int\n"
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

  protected static String getRowSQLString(DbUploadable entity) {
    String tablename = entity.getTableName();
    int id = entity.getId();

    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM ").append(tablename).append(" WHERE id = ").append(id);
    return sb.toString();
  }
}
