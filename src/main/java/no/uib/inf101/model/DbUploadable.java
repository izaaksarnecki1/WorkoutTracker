package no.uib.inf101.model;

import java.util.List;

public interface DbUploadable {
  /**
   * Retrieves the uploadable data of an object. Relevant data is
   * returned in a list.
   * @return list of relevant data
   */
  List<Object> getUploadableData();
}