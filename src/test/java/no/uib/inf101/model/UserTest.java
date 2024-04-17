package no.uib.inf101.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  @Test
  void getSetFirstName() {
    User user = new User("test", "password");
    user.setFirstName("John");
    assertEquals("John", user.getFirstName());
  }

  @Test
  void getSetLastName() {
    User user = new User("test", "password");
    user.setLastName("Doe");
    assertEquals("Doe", user.getLastName());
  }

  @Test
  void getSetWeight() {
    User user = new User("test", "password");
    user.setWeight(70);
    assertEquals(70, user.getWeight());
  }

  @Test
  void getSetHeight() {
    User user = new User("test", "password");
    user.setHeight(180);
    assertEquals(180, user.getHeight());
  }

  @Test
  void addWorkout() {
    User user = new User("test", "password");
    Workout workout = new Workout(1, LocalDate.now());
    user.addWorkout(workout);
    assertTrue(user.getWorkouts().contains(workout));
  }

  @Test
  void setId() {
    User user = new User("test", "password");
    user.setId(1);
    assertEquals(1, user.getId());
  }

  @Test
  void getId() {
    User user = new User("test", "password");
    user.setId(1);
    assertEquals(1, user.getId());
  }

  @Test
  void getUsername() {
    User user = new User("test", "password");
    assertEquals("test", user.getUsername());
  }

  @Test
  void getPassword() {
    User user = new User("test", "password");
    assertEquals("password", user.getPassword());
  }

  @Test
  void getUploadableData() {
    User user = new User("test", "password");
    HashMap<String, Object> expectedData = new HashMap<>();
    expectedData.put("username", "test");
    expectedData.put("password", "password");
    assertEquals(expectedData, user.getUploadableData());
  }

  @Test
  void getTableName() {
    assertEquals("users", User.tableName);
  }

  @Test
  void getAttributeNames() {
    User user = new User("test", "password");
    assertEquals(Arrays.asList("username", "password"), user.getAttributeNames());
  }
}