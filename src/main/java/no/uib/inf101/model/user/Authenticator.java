package no.uib.inf101.model.user;

import com.google.common.hash.Hashing;
import no.uib.inf101.db.DatabaseController;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Authenticator {
  private final String username;
  private final String password;
  public Authenticator(String username, String password) {
    // More secure if password passed in is already hashed?
    // Do I even need to store the password here?
    this.username = username;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
  }

  private static boolean checkPassword(String username, String password) {
    return DatabaseController.validatePass(username, password);
  }

  public static boolean authUser(String username, String password) {
    if (!checkUsername(username)) {
      System.out.println("Username not found. ");
      return false;
    }

    if (!checkPassword(username, password)) {
      System.out.println("Password does not match. ");
      return false;
    }

    return true;
  }

  private static boolean checkUsername(String username) {
    return DatabaseController.fetchUserId(username) == null;
  }

  public static User createNewUser(String username, String password) throws Exception {
    User user = new User(username, password);
    DatabaseController.addRow(user);
    String stringId = DatabaseController.fetchUserId(username);

    int id = 0;

    if (stringId != null) {
      id = Integer.parseInt(stringId);

    } else {
      throw new Exception("Error when assigning user id");
    }

    user.setId(id);
    return user;
  }

  public static User loginUser(String username, String password) {
    User user = new User(username, password);
    int id = Integer.parseInt(Objects.requireNonNull(DatabaseController.fetchUserId(username)));

    user.setId(id);
    return user;
  }
}
