package no.uib.inf101.model.db;

import no.uib.inf101.model.User;

import java.util.Objects;

public class Authenticator {

  public Authenticator() {

  }

  /**
   * Creates a new user if username and password are valid. Adds
   * user to database, and sets the user's id to correspond with
   * id in database.
   *
   * @param username user's username to be sent to db
   * @param password user's password to be sent to db
   * @return a new User.
   * @see User
   */
  public static User createNewUser(String username, String password) {
    if (!validateUserSignup(username, password)) {
      return null;
    }

    User user = new User(username, password);
    DatabaseController.addRow(user);
    String stringId = DatabaseController.fetchUserId(username);

    if (stringId != null) {
      user.setId(Integer.parseInt(stringId));
      return user;
    } else {
      System.err.println("Error creating user. ");
      return null;
    }
  }

  /**
   * Logs in the user by verifying username existence in db, and
   * checking password. Returns a User object with user id
   * corresponding to that which is in db.
   *
   * @param username user's username
   * @param password users' password
   * @return User object
   * @see User
   */
  public static User loginUser(String username, String password) {
    if (!validateUserLogin(username, password)) {
      return null;
    }

    User user = new User(username, password);
    String stringId = DatabaseController.fetchUserId(username);

    if (stringId != null) {
      user.setId(Integer.parseInt(stringId));
      return user;
    } else {
      System.err.println("Error creating user. ");
      return null;
    }
  }

  private static boolean validateUserSignup(String username, String password) {
    if (checkUsernameExists(username)) {
      System.out.println("Username taken. ");
      return false;
    }

    if (password.isEmpty() || password.isBlank()) {
      System.out.println("Password must consist of characters. ");
      return false;
    }

    return true;
  }

  /**
   * Method that validates a username and password. It checks if username
   * exists in db, and if given password is correct. Returns true if
   * username and password correspond with info in db.
   *
   * @param username to check
   * @param password used to validate user
   * @return true if user exits and correct password, false otherwise
   */
  private static boolean validateUserLogin(String username, String password) {
    if (checkUsernameExists(username)) {
      System.out.println("Username not found. ");
      return false;
    }

    if (!checkPassword(username, password)) {
      System.out.println("Invalid password. ");
      return false;
    }

    return true;
  }


  /**
   * Method that checks whether provided password is the same as
   * stored in db. Method looks up username in db, and compares.
   *
   * @param username used to look up password in db
   * @param password password to validate
   * @return true if password is correct, false otherwise.
   */
  private static boolean checkPassword(String username, String password) {
    return DatabaseController.validatePass(username, password);
  }

  /**
   * Method that checks whether a user exists in the database. If user exists,
   * return true, false otherwise.
   *
   * @param username to check
   * @return boolean based on username existence in db.
   */
  private static boolean checkUsernameExists(String username) {
    return Objects.equals(DatabaseController.fetchUserId(username), "0");
  }
}
