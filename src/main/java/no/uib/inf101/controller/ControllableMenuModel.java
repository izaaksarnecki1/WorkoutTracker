package no.uib.inf101.controller;

import no.uib.inf101.view.InteractiveWindow;

import java.util.Map;

/**
 * The ControllableMenuModel interface represents a model for a controllable
 * menu in a workout tracker application.
 * It provides a method to handle menu actions and update the interactive window
 * accordingly.
 */
public interface ControllableMenuModel {

  /**
   * Handles a menu action and updates the interactive window accordingly.
   *
   * @param window the interactive window to be updated
   * @param action the action to be performed
   * @param fields a map of fields associated with the action
   * @return the updated interactive window
   */
  InteractiveWindow handleMenuAction(InteractiveWindow window, String action, Map<String, String> fields);
}
