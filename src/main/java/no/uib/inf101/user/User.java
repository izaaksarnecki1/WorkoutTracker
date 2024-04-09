package no.uib.inf101.user;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.Workout;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;

public class User implements IUser {

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
  }

}
