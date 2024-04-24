package no.uib.inf101.controller;

import no.uib.inf101.Constants;
import no.uib.inf101.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
    if (this.currentWindow instanceof StartMenu startMenu) {
      if (e.getSource() == startMenu.getLoginButton()) {
        InteractiveWindow window = model.handleStartMenu(Constants.STARTMENU_BUTTON_LOGIN);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating login menu from startmenu. ");
        }
      }
      if (e.getSource() == startMenu.getSignupButton()) {
        InteractiveWindow window = model.handleStartMenu(Constants.STARTMENU_BUTTON_SIGNUP);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating signup menu from startmenu. ");
        }
      }
    } else if (this.currentWindow instanceof SignupMenu signupMenu) {
      if (e.getSource() == signupMenu.getSubmitButton()) {
        String username = signupMenu.getUsernameField().getText();
        char[] charPassword = signupMenu.getPasswordField().getPassword();

        InteractiveWindow window = model.handleSignupMenu(Constants.SIGNUPMENU_BUTTON_SUBMIT, username, charPassword);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating main menu from signup menu. ");
        }
      }
    } else if (this.currentWindow instanceof LoginMenu loginMenu) {
      if (e.getSource() == loginMenu.getSubmitButton()) {
        String username = loginMenu.getUsernameField().getText();
        char[] charPassword = loginMenu.getPasswordField().getPassword();

        InteractiveWindow window = model.handleLoginMenu(Constants.LOGINMENU_BUTTON_SUBMIT, username, charPassword);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating main menu from login menu. ");
        }
      }
    } else if (this.currentWindow instanceof MainMenu mainMenu) {
      if (e.getSource() == mainMenu.getAddWorkoutButton()) {
        model.handleMainMenu(Constants.MAINMENU_BUTTON_ADDWORKOUT);
      } else if (e.getSource() == mainMenu.getEditUserButton()) {
        InteractiveWindow window = model.handleMainMenu(Constants.MAINMENU_BUTTON_EDITUSER);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating profile menu from main menu. ");
        }
      } else if (e.getSource() == mainMenu.getViewWorkoutsButton()) {
        model.handleMainMenu(Constants.MAINMENU_BUTTON_VIEWWORKOUTS);
      }
    } else if (this.currentWindow instanceof ProfileMenu profileMenu) {
      if (e.getSource() == profileMenu.getSaveButton()) {
        Map<String, String> fields = profileMenu.getFields();
        InteractiveWindow window = model.handleProfileMenu(Constants.PROFILEMENU_BUTTON_SAVE, fields);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating main menu from profile menu. ");
        }
      }
    }
  }

  private void setNewWindow(InteractiveWindow window) {
    this.currentWindow.dispose();
    this.currentWindow = window;
    this.currentWindow.addActionListener(this);
  }
}
