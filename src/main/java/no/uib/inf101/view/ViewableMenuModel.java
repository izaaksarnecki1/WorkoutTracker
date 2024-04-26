package no.uib.inf101.view;

import java.util.ArrayList;
import java.util.Map;

// Use to get user data from model to view.
/**
 * The ViewableMenuModel interface represents a model for a viewable menu in a workout tracker application.
 * It provides methods to retrieve the user profile information.
 */
/**
 * The ViewableMenuModel interface represents a model for a menu that can be viewed by the user.
 * It provides methods to retrieve the user profile, workout display information, and check if a workout exists.
 */
public interface ViewableMenuModel {

  /**
   * Retrieves the user profile as a map of key-value pairs.
   *
   * @return The user profile as a map, where the keys represent the profile attributes and the values represent the corresponding attribute values
   */
  Map<String, String> getUserProfile();

  /**
   * Returns a map containing the workout display information.
   *
   * @return a map where the keys are workout names and the values are the corresponding display information
   */
  Map<String, String> getWorkoutDisplay();

  /**
   * Checks if a workout exists in the current model. Mainly used when going from AddExerciseMenu to AddWorkoutMenu.
   *
   * @return true if a workout exists, false otherwise
   */
  boolean workoutExists();

  /**
   * Retrieves the workout data as a two-dimensional ArrayList of Strings.
   *
   * @return The workout data, where each inner ArrayList represents a row of data.
   */
  ArrayList<ArrayList<String>> getWorkoutData();

  /**
   * Retrieves the exercise data for a given workout as a two-dimensional ArrayList of Strings.
   *
   * @param workoutId the ID of the workout
   * @return The exercise data, where each inner ArrayList represents a row of data.
   */
  ArrayList<ArrayList<String>> getExerciseData(int workoutId);
}
