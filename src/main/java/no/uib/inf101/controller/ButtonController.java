package no.uib.inf101.controller;

import no.uib.inf101.Constants;
import no.uib.inf101.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The ButtonController class implements the ActionListener interface and handles the actions performed on interactive windows.
 * It is responsible for delegating the actions to the appropriate methods based on the type of the current window.
 */
public class ButtonController implements ActionListener {
  private final ControllableMenuModel model;
  private InteractiveWindow currentWindow;

  public ButtonController(ControllableMenuModel model, InteractiveWindow window) {
    this.model = model;
    this.currentWindow = window;
    this.currentWindow.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      if (currentWindow instanceof StartMenu) {
        handleStartMenuActions((StartMenu) currentWindow, e);
      } else if (currentWindow instanceof SignupMenu) {
        handleSignupMenuActions((SignupMenu) currentWindow, e);
      } else if (currentWindow instanceof LoginMenu) {
        handleLoginMenuActions((LoginMenu) currentWindow, e);
      } else if (currentWindow instanceof MainMenu) {
        handleMainMenuActions((MainMenu) currentWindow, e);
      } else if (currentWindow instanceof ProfileMenu) {
        handleProfileMenuActions((ProfileMenu) currentWindow, e);
      } else if (currentWindow instanceof AddWorkoutMenu) {
        handleAddWorkoutMenuActions((AddWorkoutMenu) currentWindow, e);
      } else if (currentWindow instanceof AddExerciseMenu) {
        handleAddExerciseMenuActions((AddExerciseMenu) currentWindow, e);
      } else if (currentWindow instanceof ViewWorkoutMenu) {
        handleViewWorkoutMenuActions((ViewWorkoutMenu) currentWindow, e);
      } else {
        System.err.println("Unknown window type.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void handleMenuAction(InteractiveWindow window, String action) {
    Map<String, String> fields = null;
    if (window instanceof MenuWithFields) {
      fields = ((MenuWithFields) window).getFields();
    }
    InteractiveWindow newWindow = model.handleMenuAction(window, action, fields);
    if (newWindow != null) {
      setNewWindow(newWindow);
    } else {
      System.err.println("Failed creating menu from " + action);
    }
  }

  private void handleStartMenuActions(StartMenu startMenu, ActionEvent e) {
    if (e.getSource() == startMenu.getLoginButton()) {
      handleMenuAction(startMenu, Constants.STARTMENU_BUTTON_LOGIN);
    } else if (e.getSource() == startMenu.getSignupButton()){
      handleMenuAction(startMenu, Constants.STARTMENU_BUTTON_SIGNUP);
    }
  }

  private void handleSignupMenuActions(SignupMenu signupMenu, ActionEvent e) {
    if (e.getSource() == signupMenu.getSubmitButton()) {
      handleMenuAction(signupMenu, Constants.SIGNUPMENU_BUTTON_SUBMIT);
    } else if (e.getSource() == signupMenu.getBackButton()) {
      handleMenuAction(signupMenu, Constants.SIGNUPMENU_BUTTON_BACK);
    }
  }

  private void handleLoginMenuActions(LoginMenu loginMenu, ActionEvent e) {
    if (e.getSource() == loginMenu.getSubmitButton()) {
      handleMenuAction(loginMenu, Constants.LOGINMENU_BUTTON_SUBMIT);
    } else if (e.getSource() == loginMenu.getBackButton()) {
      handleMenuAction(loginMenu, Constants.LOGINMENU_BUTTON_BACK);
    }
  }

  private void handleMainMenuActions(MainMenu mainMenu, ActionEvent e) {
    if (e.getSource() == mainMenu.getAddWorkoutButton()) {
      handleMenuAction(mainMenu, Constants.MAINMENU_BUTTON_ADDWORKOUT);
    } else if (e.getSource() == mainMenu.getEditUserButton()) {
      handleMenuAction(mainMenu, Constants.MAINMENU_BUTTON_EDITUSER);
    } else if (e.getSource() == mainMenu.getViewWorkoutsButton()) {
      handleMenuAction(mainMenu, Constants.MAINMENU_BUTTON_VIEWWORKOUTS);
    }
  }

  private void handleProfileMenuActions(ProfileMenu profileMenu, ActionEvent e) {
    if (e.getSource() == profileMenu.getSaveButton()) {
      handleMenuAction(profileMenu, Constants.PROFILEMENU_BUTTON_SAVE);
    }
  }

  private void handleAddWorkoutMenuActions(AddWorkoutMenu addWorkoutMenu, ActionEvent e) {
    if (e.getSource() == addWorkoutMenu.getAddExerciseButton()) {
      handleMenuAction(addWorkoutMenu, Constants.ADDWORKOUT_BUTTON_ADD);
    } else if (e.getSource() == addWorkoutMenu.getBackButton()) {
      handleMenuAction(addWorkoutMenu, Constants.ADDWORKOUT_BUTTON_BACK);
    }
  }

  private void handleAddExerciseMenuActions(AddExerciseMenu addExerciseMenu, ActionEvent e) {
    if (e.getSource() == addExerciseMenu.getAddButton()) {
      handleMenuAction(addExerciseMenu, Constants.ADDEXERCISE_BUTTON_ADD);
    }
  }

  private void handleViewWorkoutMenuActions(ViewWorkoutMenu viewWorkoutMenu, ActionEvent e) {
    if (e.getSource() == viewWorkoutMenu.getBackButton()) {
      handleMenuAction(viewWorkoutMenu, Constants.VIEWWORKOUTS_BUTTON_BACK);
    } else if (e.getSource() == viewWorkoutMenu.getNextPage()) {
      handleMenuAction(viewWorkoutMenu, Constants.VIEWWORKOUTS_BUTTON_NEXT);
    } else if (e.getSource() == viewWorkoutMenu.getPrevPage()) {
      handleMenuAction(viewWorkoutMenu, Constants.VIEWWORKOUTS_BUTTON_PREV);
    }
  }

  private void setNewWindow(InteractiveWindow window) {
    this.currentWindow.dispose();
    this.currentWindow = window;
    this.currentWindow.addActionListener(this);
  }
}
