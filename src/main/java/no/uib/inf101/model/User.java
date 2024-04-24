package no.uib.inf101.model;

import java.util.*;

public class User implements DbUploadable {

  public static final String tableName = "users";
  private final String username;
  private final String password;
  private String firstName;
  private String lastName;
  private int weight;
  private int height;
  private ArrayList<Workout> workouts;
  private int id = 0;
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String FIRST_NAME = "first_name";
  public static final String LAST_NAME = "last_name";
  public static final String WEIGHT = "weight";
  public static final String HEIGHT = "height";
  private final String[] profileAttributes = {FIRST_NAME, LAST_NAME, WEIGHT, HEIGHT};

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.firstName = "";
    this.lastName = "";
    this.weight = 0;
    this.height = 0;
    this.workouts = new ArrayList<>();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void addWorkout(Workout workout) {
    this.workouts.add(workout);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }
  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
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
    return tableName;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return new ArrayList<>(Arrays.asList(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, WEIGHT, HEIGHT));
  }

  public String[] getProfileAttributes() {
    return Arrays.copyOf(this.profileAttributes, this.profileAttributes.length);
  }

//  public Map<String, String>

//  public ArrayList<Workout> getWorkouts() {
//    return this.workouts;
//  }
}
