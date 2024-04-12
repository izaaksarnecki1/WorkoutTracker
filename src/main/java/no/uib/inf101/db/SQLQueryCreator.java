package no.uib.inf101.db;

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
}
