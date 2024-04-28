package no.uib.inf101.model.db;

import no.uib.inf101.model.User;

import java.util.Objects;

/**
 * The Authenticator class is responsible for creating and logging in users.
 * It interacts with a database controller to perform operations such as
 * inserting user data, fetching user IDs, and validating passwords.
 */
public class Authenticator {

  private DatabaseController databaseController;

  public Authenticator(DatabaseController databaseController) {
    this.databaseController = databaseController;
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
  public User createNewUser(String username, String password) {
    if (!validateUserSignup(username, password)) {
      return null;
    }

    User user = new User(username, password);
    this.databaseController.insertRow(user);
    String stringId = this.databaseController.fetchUserId(username);

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
  public User loginUser(String username, String password) {
    if (!validateUserLogin(username, password)) {
      return null;
    }

    User user = new User(username, password);
    String stringId = this.databaseController.fetchUserId(username);

    if (stringId != null) {
      user.setId(Integer.parseInt(stringId));
      return user;
    } else {
      System.err.println("Error creating user. ");
      return null;
    }
  }

  /**
   * Validates a user signup by checking if the username is available and if the password is valid.
   *
   * @param username the username to be checked
   * @param password the password to be checked
   * @return true if the user signup is valid, false otherwise
   */
  private boolean validateUserSignup(String username, String password) {
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
  private boolean validateUserLogin(String username, String password) {
    if (!checkUsernameExists(username)) {
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
  private boolean checkPassword(String username, String password) {
    return this.databaseController.validatePass(username, password);
  }

  /**
   * Method that checks whether a user exists in the database. If user exists,
   * return true, false otherwise.
   *
   * @param username to check
   * @return boolean based on username existence in db.
   */
  private boolean checkUsernameExists(String username) {
    return !Objects.equals(this.databaseController.fetchUserId(username), "0");
  }
}
