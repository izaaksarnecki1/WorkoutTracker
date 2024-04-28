package no.uib.inf101.model;

import com.google.common.hash.Hashing;
import no.uib.inf101.Constants;
import no.uib.inf101.controller.ControllableMenuModel;
import no.uib.inf101.model.db.Authenticator;
import no.uib.inf101.model.db.DatabaseController;
import no.uib.inf101.view.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The MenuModel class represents the model component of the menu system in the
 * workout tracker application.
 * It implements both the ControllableMenuModel and ViewableMenuModel
 * interfaces.
 * The MenuModel class handles user actions and manages the state of the
 * application's menus.
 * It interacts with the DatabaseController and Authenticator classes to perform
 * database operations and user authentication.
 */
public class MenuModel implements ControllableMenuModel, ViewableMenuModel {

  private User user = null;
  private Workout workout = null;
  private int currentWorkout;
  private DatabaseController databaseController;
  private Authenticator authenticator;

  /**
   * Constructs a new MenuModel object with the specified DatabaseController.
   * 
   * @param databaseController the DatabaseController to be used by the MenuModel
   */
  public MenuModel(DatabaseController databaseController) {
    this.databaseController = databaseController;
    this.authenticator = new Authenticator(databaseController);
    this.currentWorkout = 0;
  }

  @Override
  public InteractiveWindow handleMenuAction(InteractiveWindow window, String action, Map<String, String> fields) {
    if (window instanceof SignupMenu) {
      return handleSignupMenu(action, fields);
    } else if (window instanceof LoginMenu) {
      return handleLoginMenu(action, fields);
    } else if (window instanceof StartMenu) {
      return handleStartMenu(action);
    } else if (window instanceof MainMenu) {
      return handleMainMenu(action);
    } else if (window instanceof ProfileMenu) {
      return handleProfileMenu(action, fields);
    } else if (window instanceof AddWorkoutMenu) {
      return handleAddWorkoutMenu(action, fields);
    } else if (window instanceof AddExerciseMenu) {
      return handleAddExerciseMenu(action, fields);
    } else if (window instanceof ViewWorkoutMenu) {
      return handleViewWorkoutMenu(action);
    } else {
      System.err.println("Unknown window type.");
    }
    return null;
  }

  @Override
  public Map<String, String> getUserProfile() {
    Map<String, String> profileData = new HashMap<>();
    if (this.user != null) {
      profileData.put(User.FIRST_NAME, this.user.getFirstName());
      profileData.put(User.LAST_NAME, this.user.getLastName());
      profileData.put(User.WEIGHT, String.valueOf(this.user.getWeight()));
      profileData.put(User.HEIGHT, String.valueOf(this.user.getHeight()));
    }
    return profileData;
  }

  @Override
  public Map<String, String> getWorkoutDisplay() {
    Map<String, String> workoutAttributes = new HashMap<>();
    if (workout == null) {
      return workoutAttributes;
    }
    workoutAttributes.put(Workout.WORKOUTNAME, this.workout.getWorkoutName());
    workoutAttributes.put(Workout.WORKOUTDATE, this.workout.getWorkoutDate());
    return workoutAttributes;
  }

  @Override
  public ArrayList<ArrayList<String>> getWorkoutData() {
    return this.databaseController.getUserWorkouts(user);
  }

  @Override
  public ArrayList<String> getCurrentWorkout() {
    ArrayList<ArrayList<String>> userWorkouts = this.databaseController.getUserWorkouts(user); 
    if (userWorkouts.isEmpty()) {
      return null;
    }
    return userWorkouts.get(currentWorkout);
  }

  @Override
  public ArrayList<ArrayList<String>> getExerciseData(int id) {
    return this.databaseController.getWorkoutExercises(id);
  }

  @Override
  public boolean workoutExists() {
    return this.workout != null;
  }

  private InteractiveWindow handleSignupMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.SIGNUPMENU_BUTTON_SUBMIT)) {
      return handeSignupSubmit(fields);
    } else if (identifier.equals(Constants.SIGNUPMENU_BUTTON_BACK)) {
      return new StartMenu();
    }
    return null;
  }

  private InteractiveWindow handeSignupSubmit(Map<String, String> fields) {
    String stringPassword = hashPassword(fields.get(Constants.SIGNUPMENU_FIELD_PASSWORD));
    User newUser = this.authenticator.createNewUser(fields.get(Constants.SIGNUPMENU_FIELD_USERNAME), stringPassword);
    if (newUser != null) {
      this.user = newUser;
      return new MainMenu();
    } else {
      System.err.println("Error creating user. ");
      return null;
    }
  }

  private String hashPassword(String passwordString) {
    return Hashing
        .sha256()
        .hashString(passwordString, StandardCharsets.UTF_8)
        .toString();
  }

  private InteractiveWindow handleLoginMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.LOGINMENU_BUTTON_SUBMIT)) {
      return handleLoginSubmit(fields);
    } else if (identifier.equals(Constants.LOGINMENU_BUTTON_BACK)) {
      return new StartMenu();
    }
    return null;
  }

  private InteractiveWindow handleLoginSubmit(Map<String, String> fields) {
    String stringPassword = hashPassword(fields.get(Constants.LOGINMENU_FIELD_PASSWORD));
    User loggedInUser = this.authenticator.loginUser(fields.get(Constants.LOGINMENU_FIELD_USERNAME), stringPassword);
    if (loggedInUser != null) {
      this.user = loggedInUser;
      setUserDbAttributes();
      return new MainMenu();
    } else {
      System.err.println("Error logging in user. ");
      return null;
    }
  }

  private InteractiveWindow handleStartMenu(String identifier) {
    if (identifier.equals(Constants.STARTMENU_BUTTON_SIGNUP)) {
      return new SignupMenu();
    } else if (identifier.equals(Constants.STARTMENU_BUTTON_LOGIN)) {
      return new LoginMenu();
    }
    return null;
  }

  private InteractiveWindow handleMainMenu(String identifier) {
    if (identifier.equals(Constants.MAINMENU_BUTTON_EDITUSER)) {
      return new ProfileMenu(this);
    } else if (identifier.equals(Constants.MAINMENU_BUTTON_ADDWORKOUT)) {
      return new AddWorkoutMenu(this);
    } else if (identifier.equals(Constants.MAINMENU_BUTTON_VIEWWORKOUTS)) {
      return new ViewWorkoutMenu(this);
    }
    return null;
  }

  private InteractiveWindow handleProfileMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.PROFILEMENU_BUTTON_SAVE)) {
      user.setFirstName(fields.get(Constants.PROFILEMENU_FIELD_FIRST));
      user.setLastName(fields.get(Constants.PROFILEMENU_FIELD_LAST));
      user.setWeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_WEIGHT)));
      user.setHeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_HEIGHT)));
      this.databaseController.updateRow(user);
      return new MainMenu();
    }
    return null;
  }

  private InteractiveWindow handleAddWorkoutMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.ADDWORKOUT_BUTTON_ADD)) {
      if (fields == null) {
        System.err.println("Error adding workout. Fields are null");
        return null;
      }
      setupWorkout(fields);
      return new AddExerciseMenu();
    } else if (identifier.equals(Constants.ADDWORKOUT_BUTTON_BACK)) {
      this.workout = null;
      return new MainMenu();
    } else if (identifier.equals(Constants.ADDWORKOUT_BUTTON_SAVE)) {
      if (fields == null) {
        System.err.println("Error saving workout. Fields are null");
        return null;
      }
      setupWorkout(fields);
      this.databaseController.updateRow(workout);
    }
    return null;
  }

  private InteractiveWindow handleAddExerciseMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.ADDEXERCISE_BUTTON_ADD)) {
      if (fields == null) {
        System.err.println("Error adding exercise. Fields are null");
        return null;
      }
      Exercise exercise = new Exercise(this.workout.getId(), fields.get(Constants.ADDEXERCISE_FIELD_EX_NAME),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_REPS)),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_SETS)),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_WEIGHT)));
      this.databaseController.insertRow(exercise);
      return new AddWorkoutMenu(this);
    }
    return null;
  }

  private InteractiveWindow handleViewWorkoutMenu(String identifier) {
    if (identifier.equals(Constants.VIEWWORKOUTS_BUTTON_BACK)) {
      this.currentWorkout = 0;
      return new MainMenu();
    } else if (identifier.equals(Constants.VIEWWORKOUTS_BUTTON_NEXT)) {
      if (this.currentWorkout < this.databaseController.getUserWorkouts(user).size() - 1) {
        this.currentWorkout++;
        return new ViewWorkoutMenu(this);
      } else {
        return null;
      }
    } else if (identifier.equals(Constants.VIEWWORKOUTS_BUTTON_PREV)) {
      if (this.currentWorkout > 0) {
        this.currentWorkout--;
        return new ViewWorkoutMenu(this);
      } else {
        return null;
      }
    }
    return null;
  }

  private void setupWorkout(Map<String, String> fields) {
    if (workout == null) {
      this.workout = new Workout(this.user.getId(), fields.get(Constants.ADDWORKOUT_FIELD_DATE),
          fields.get(Constants.ADDWORKOUT_FIELD_WORKOUTNAME));
      this.databaseController.insertRow(workout);
      int id = Integer.parseInt(this.databaseController.getLastId(this.workout));
      workout.setWorkoutId(id);
    } else {
      workout.setWorkoutDate(fields.get(Constants.ADDWORKOUT_FIELD_DATE));
      workout.setWorkoutName(fields.get(Constants.ADDWORKOUT_FIELD_WORKOUTNAME));
    }
  }

  private void setUserDbAttributes() {
    Map<String, String> userAttributes = this.databaseController.getRow(user);
    user.setFirstName(userAttributes.get(User.FIRST_NAME));
    user.setLastName(userAttributes.get(User.LAST_NAME));
    user.setWeight(Integer.parseInt(userAttributes.get(User.WEIGHT)));
    user.setHeight(Integer.parseInt(userAttributes.get(User.HEIGHT)));
  }
}
