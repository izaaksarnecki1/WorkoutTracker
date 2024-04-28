package no.uib.inf101.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The DbUploadable interface represents an object that can be uploaded to a database.
 * It provides methods to retrieve relevant data, table name, attribute names, and parent table.
 */
public interface DbUploadable {
  /**
   * Retrieves the uploadable data of an object. Relevant data is
   * returned in a HashMap, where the keys are attribute names and the values are the corresponding values.
   *
   * @return a HashMap containing the relevant data of the object
   */
  HashMap<String, Object> getUploadableData();

  /**
   * Retrieves the table name associated with the object.
   *
   * @return the table name for the object
   */
  String getTableName();

  /**
   * Retrieves all named attributes that are to be stored in the database.
   *
   * @return a list of attribute names
   */
  ArrayList<String> getAttributeNames();

  /**
   * Retrieves the ID of the object.
   *
   * @return the ID of the object
   */
  int getId();

  default void setId(int id) {return;};
}
