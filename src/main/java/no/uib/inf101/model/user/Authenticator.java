package no.uib.inf101.model.user;

import com.google.common.hash.Hashing;
import no.uib.inf101.db.DatabaseController;

import java.nio.charset.StandardCharsets;

public class Authenticator {
  private final DatabaseController databaseController;
  private final String username;
  private final String password;
  public Authenticator(String username, String password) {
    // More secure if password passed in is already hashed?

    this.username = username;
    this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    this.databaseController = new DatabaseController();
    checkIfUsernameExist();
  }

  private boolean checkIfUsernameExist() {
    return this.databaseController.fetchUserId(this.username) == null;
  }

  public static boolean checkUsername(String username) {
//    return DatabaseController.fetchUserId(username) == null;
    return false;
  }
}
