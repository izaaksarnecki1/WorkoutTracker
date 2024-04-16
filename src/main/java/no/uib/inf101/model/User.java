package no.uib.inf101.model;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.Workout;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class User implements DbUploadable {

  public static final String tableName = "users";
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private int weight;
  private int height;
  private ArrayList<Workout> workouts;
  private int id = 0;

  public User(String username, String password) {
    this.username = username;
    this.password = password; // Password is already hashed in parameter
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
    uploadableData.put("username", this.username);
    uploadableData.put("password", this.password);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return tableName;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return new ArrayList<>(Arrays.asList("username", "password"));
  }
}
