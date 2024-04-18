package no.uib.inf101.controller;

import no.uib.inf101.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ButtonController implements ActionListener {
//  private final ControllableMenuModel model;
  private InteractiveWindow currentWindow;
  private Map<String, InteractiveWindow> windowMap;

  public ButtonController(InteractiveWindow window) {
//    this.model = model;
    this.currentWindow = window;
    this.windowMap = new HashMap<>();
    this.windowMap.put(currentWindow.getIdentifier(), window);
    this.currentWindow.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.currentWindow instanceof StartMenu startMenu) {
      if (e.getSource() == startMenu.getLoginButton()) {
        InteractiveWindow loginMenu = new LoginMenu();
        this.setNewWindow(loginMenu);
      }
      if (e.getSource() == startMenu.getSignupButton()) {
        InteractiveWindow signupMenu = new SignupMenu();
        this.setNewWindow(signupMenu);
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
