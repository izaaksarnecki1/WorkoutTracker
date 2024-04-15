package no.uib.inf101.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface DbUploadable {
  /**
   * Retrieves the uploadable data of an object. Relevant data is
   * returned in a list.
   *
   * @return list of relevant data
   */
  HashMap<String, Object> getUploadableData();

  /**
   * Retrieves the table name associated with the object.
   *
   * @return table name for object
   */
  String getTableName();

  /**
   * Retrieves all named attributes that are to be stored in the
   * database.
   *
   * @return List of attributes names.
   */
  ArrayList<String> getAttributeNames();

  /**
   * Retrieves the parent table of current object.
   * Exercise would return workouts as exercise links to workouts
   * through foreign key. Not all objects will have parents,
   * returns null by default.
   *
   * @return Parent table of object.
   */
  default String getParent() {return null;}
}
