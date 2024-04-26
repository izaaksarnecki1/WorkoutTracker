package no.uib.inf101.controller;

import no.uib.inf101.view.InteractiveWindow;

import java.util.Map;

/**
 * The ControllableMenuModel interface represents a model for handling menu actions in the workout tracker application.
 * It provides methods for handling various menu actions such as signup, login, start, main, profile, add workout, and add exercise.
 */
public interface ControllableMenuModel {
  /**
   * Handles the signup menu action.
   *
   * @param identifier the identifier for the menu
   * @param uname the username for the signup
   * @param pass the password for the signup
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleSignupMenu(String identifier, String uname, char[] pass);

  /**
   * Handles the login menu action.
   *
   * @param identifier the identifier for the menu
   * @param fields a map of additional fields for the login
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleLoginMenu(String identifier, Map<String, String> fields);

  /**
   * Handles the start menu action.
   *
   * @param identifier the identifier for the menu
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleStartMenu(String identifier);

  /**
   * Handles the main menu action.
   *
   * @param identifier the identifier for the menu
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleMainMenu(String identifier);

  /**
   * Handles the profile menu action.
   *
   * @param identifier the identifier for the menu
   * @param fields a map of profile fields and their values
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleProfileMenu(String identifier, Map<String, String> fields);

  /**
   * Handles the add workout menu action.
   *
   * @param identifier the identifier for the menu
   * @param fields a map of workout fields and their values
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleAddWorkoutMenu(String identifier, Map<String, String> fields);

  /**
   * Handles the add workout menu action with default values.
   *
   * @param identifier the identifier for the menu
   * @return an InteractiveWindow representing the next menu to display
   */
  default InteractiveWindow handleAddWorkoutMenu(String identifier) {
    return handleAddWorkoutMenu(identifier, null);
  };


  /**
   * Handles the add exercise menu action.
   *
   * @param identifier the identifier for the menu
   * @param fields a map of exercise fields and their values
   * @return an InteractiveWindow representing the next menu to display
   */
  InteractiveWindow handleAddExerciseMenu(String identifier, Map<String, String> fields);
}
