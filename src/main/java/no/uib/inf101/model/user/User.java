package no.uib.inf101.model.user;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.Workout;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class User implements DbUploadable {

  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private int weight;
  private int height;
  private ArrayList<Workout> workouts;

  public User(String username, String password) {
    this.username = username;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
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

  @Override
  public HashMap<String, Object> getUploadableData() {
    HashMap<String, Object> uploadableData = new HashMap<>();
    uploadableData.put("username", this.username);
    uploadableData.put("password", this.password);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return "users";
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return new ArrayList<>(Arrays.asList("username", "password"));
  }
}
