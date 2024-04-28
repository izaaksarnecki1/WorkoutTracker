package no.uib.inf101.model;

import java.util.*;

import org.jetbrains.annotations.TestOnly;

/**
 * Represents a user in the workout tracker system.
 * Implements the DbUploadable interface for database operations.
 */
public class User implements DbUploadable {

  public static final String TABLE_NAME = "users";
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String FIRST_NAME = "first_name";
  public static final String LAST_NAME = "last_name";
  public static final String WEIGHT = "weight";
  public static final String HEIGHT = "height";

  private final ArrayList<String> attributeNames = new ArrayList<>(
      Arrays.asList(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, WEIGHT, HEIGHT));

  private final String username;
  private final String password;
  private String firstName;
  private String lastName;
  private int weight;
  private int height;
  private int id = 0;

  /**
   * Constructs a new User object with the specified username and password.
   * @param username the username of the user
   * @param password the password of the user
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.firstName = "";
    this.lastName = "";
    this.weight = 0;
    this.height = 0;
  }

  /**
   * Returns the first name of the user.
   * @return the first name of the user
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Sets the first name of the user.
   * @param firstName the first name to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Returns the last name of the user.
   * @return the last name of the user
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the user.
   * @param lastName the last name to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Returns the weight of the user.
   * @return the weight of the user
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the user.
   * @param weight the weight to set
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Returns the height of the user.
   * @return the height of the user
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of the user.
   * @param height the height to set
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Sets the ID of the user.
   * @param id the ID to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns the ID of the user.
   * @return the ID of the user
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the username of the user.
   * @return the username of the user
   */
  @TestOnly
  public String getUsername() {
    return this.username;
  }

  @Override
  public HashMap<String, Object> getUploadableData() {
    HashMap<String, Object> uploadableData = new HashMap<>();
    uploadableData.put(USERNAME, this.username);
    uploadableData.put(PASSWORD, this.password);
    uploadableData.put(FIRST_NAME, this.firstName);
    uploadableData.put(LAST_NAME, this.lastName);
    uploadableData.put(WEIGHT, this.weight);
    uploadableData.put(HEIGHT, this.height);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return User.TABLE_NAME;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return this.attributeNames;
  }
}
