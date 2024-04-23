package no.uib.inf101.controller;

import no.uib.inf101.Constants;
import no.uib.inf101.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ButtonController implements ActionListener {
  private final ControllableMenuModel model;
  private InteractiveWindow currentWindow;
  private Map<String, InteractiveWindow> windowMap;

  public ButtonController(ControllableMenuModel model, InteractiveWindow window) {
    this.model = model;
    this.currentWindow = window;
    this.windowMap = new HashMap<>();
    this.windowMap.put(currentWindow.getIdentifier(), window);
    this.currentWindow.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.currentWindow instanceof StartMenu startMenu) {
      if (e.getSource() == startMenu.getLoginButton()) {
        InteractiveWindow window = model.handleStartMenu(Constants.STARTMENU_LOGIN);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating login menu from startmenu. ");
        }
      }
      if (e.getSource() == startMenu.getSignupButton()) {
        InteractiveWindow window = model.handleStartMenu(Constants.STARTMENU_LOGIN);
        if (window != null) {
          this.setNewWindow(window);
        } else {
          System.err.println("Failed creating signup menu from startmenu. ");
        }
      }
    } else if (this.currentWindow instanceof SignupMenu signupMenu) {
      if (e.getSource() == signupMenu.getSubmitButton()) {
        System.out.println("pressed submit button");
      }
    } else if (this.currentWindow instanceof LoginMenu loginMenu) {
      // Code here
    } else if (this.currentWindow instanceof MainMenu mainMenu) {
      // Code here
    }
  }

  private void setNewWindow(InteractiveWindow window) {
    this.currentWindow.dispose();
    this.currentWindow = window;
    this.currentWindow.addActionListener(this);
  }
}
