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

public class MenuModel implements ControllableMenuModel, ViewableMenuModel {

  private User user = null;
  private Workout workout = null;

  public MenuModel() {
  }

  @Override
  public InteractiveWindow handleSignupMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.SIGNUPMENU_BUTTON_SUBMIT)) {
      String stringPassword = Hashing
          .sha256()
          .hashString(String.valueOf(fields.get(Constants.SIGNUPMENU_FIELD_PASSWORD)), StandardCharsets.UTF_8)
          .toString();
      User user = Authenticator.createNewUser(fields.get(Constants.SIGNUPMENU_FIELD_USERNAME), stringPassword);
      if (user != null) {
        this.user = user;
        return new MainMenu();
      } else {
        System.err.println("Error creating user. ");
      }
    } else if (identifier.equals(Constants.SIGNUPMENU_BUTTON_BACK)) {
      return new StartMenu();
    }
    return null;
  }

  @Override
  public InteractiveWindow handleLoginMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.LOGINMENU_BUTTON_SUBMIT)) {
      String stringPassword = Hashing
          .sha256()
          .hashString(String.valueOf(fields.get(Constants.LOGINMENU_FIELD_PASSWORD)), StandardCharsets.UTF_8)
          .toString();
      User user = Authenticator.loginUser(fields.get(Constants.LOGINMENU_FIELD_USERNAME), stringPassword);
      if (user != null) {
        this.user = user;
        setUserDbAttributes();
        return new MainMenu();
      } else {
        System.err.println("Error logging in user. ");
      }
    } else if (identifier.equals(Constants.LOGINMENU_BUTTON_BACK)) {
      return new StartMenu();
    }
    return null;
  }

  @Override
  public InteractiveWindow handleStartMenu(String identifier) {
    if (identifier.equals(Constants.STARTMENU_BUTTON_SIGNUP)) {
      return new SignupMenu();
    } else if (identifier.equals(Constants.STARTMENU_BUTTON_LOGIN)) {
      return new LoginMenu();
    }
    return null;
  }

  @Override
  public InteractiveWindow handleMainMenu(String identifier) {
    if (identifier.equals(Constants.MAINMENU_BUTTON_EDITUSER)) {
      return new ProfileMenu(this);
    } else if (identifier.equals(Constants.MAINMENU_BUTTON_ADDWORKOUT)) {
      return new AddWorkoutMenu(this);
    } else if (identifier.equals(Constants.MAINMENU_BUTTON_VIEWWORKOUTS)) {
      return new ViewWorkoutMenu(this);
    } 
    return null;
  }

  @Override
  public InteractiveWindow handleProfileMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.PROFILEMENU_BUTTON_SAVE)) {
      user.setFirstName(fields.get(Constants.PROFILEMENU_FIELD_FIRST));
      user.setLastName(fields.get(Constants.PROFILEMENU_FIELD_LAST));
      user.setWeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_WEIGHT)));
      user.setHeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_HEIGHT)));
      DatabaseController.updateRow(user);
      return new MainMenu();
    }
    return null;
  }

  @Override
  public InteractiveWindow handleAddWorkoutMenu(String identifier, Map<String, String> fields) {
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
      DatabaseController.updateRow(workout);
    }
    return null;
  }

  @Override
  public InteractiveWindow handleAddExerciseMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.ADDEXERCISE_BUTTON_ADD)) {
      if (fields == null) {
        System.err.println("Error adding exercise. Fields are null");
        return null;
      }
      Exercise exercise = new Exercise(this.workout.getId(), fields.get(Constants.ADDEXERCISE_FIELD_EX_NAME),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_REPS)),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_SETS)),
          Integer.parseInt(fields.get(Constants.ADDEXERCISE_FIELD_WEIGHT)));
      DatabaseController.insertRow(exercise);
      return new AddWorkoutMenu(this);
    }
    return null;
  }

  @Override
  public InteractiveWindow handleViewWorkoutMenu(String identifier) {
    if (identifier.equals(Constants.VIEWWORKOUTS_BUTTON_BACK)) {
      return new MainMenu();
    }
    return null;
  }

  @Override
  public Map<String, String> getUserProfile() {
    Map<String, String> profileData = new HashMap<>();
    profileData.put(User.FIRST_NAME, this.user.getFirstName());
    profileData.put(User.LAST_NAME, this.user.getLastName());
    profileData.put(User.WEIGHT, String.valueOf(this.user.getWeight()));
    profileData.put(User.HEIGHT, String.valueOf(this.user.getHeight()));
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
    return DatabaseController.getUserWorkouts(user);
  }

  @Override
  public ArrayList<ArrayList<String>> getExerciseData(int id) {
    return DatabaseController.getWorkoutExercises(id);
  }

  @Override
  public boolean workoutExists() {
    return this.workout != null;
  }

  private void setupWorkout(Map<String, String> fields) {
    if (workout == null) {
      this.workout = new Workout(this.user.getId(), fields.get(Constants.ADDWORKOUT_FIELD_DATE),
          fields.get(Constants.ADDWORKOUT_FIELD_WORKOUTNAME));
      DatabaseController.insertRow(workout);
      int id = Integer.parseInt(DatabaseController.getLastId(this.workout));
      workout.setWorkoutId(id);
    } else {
      workout.setWorkoutDate(fields.get(Constants.ADDWORKOUT_FIELD_DATE));
      workout.setWorkoutName(fields.get(Constants.ADDWORKOUT_FIELD_WORKOUTNAME));
    }
  }

  private void setUserDbAttributes() {
    Map<String, String> userAttributes = DatabaseController.getRow(user);
    user.setFirstName(userAttributes.get(User.FIRST_NAME));
    user.setLastName(userAttributes.get(User.LAST_NAME));
    user.setWeight(Integer.parseInt(userAttributes.get(User.WEIGHT)));
    user.setHeight(Integer.parseInt(userAttributes.get(User.HEIGHT)));
  }
}
