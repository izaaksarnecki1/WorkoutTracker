package no.uib.inf101.controller;

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
    this.windowMap.put(currentWindow.getIdentifier(), window);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.currentWindow instanceof StartMenu) {
      if (e.getSource() == this.currentWindow.getLoginButton()) {
        InteractiveWindow loginMenu = new LoginMenu();
//        this.currentWindow.dispose();
      }
      if (e.getSource() == this.currentWindow.getSignupButton()) {
        InteractiveWindow signupMenu = new SignupMenu();
        startMenu.dispose();
      }
    } else if (this.currentWindow instanceof SignupMenu) {
      // Code here
    } else if (this.currentWindow instanceof LoginMenu) {
      // Code here
    } else if (this.currentWindow instanceof MainMenu) {
      // Code here
    }
  }
}
