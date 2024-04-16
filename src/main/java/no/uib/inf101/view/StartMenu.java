package no.uib.inf101.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class StartMenu extends InteractiveWindow {

  private final JButton startButton;
  private final JButton loginButton;
  private final JButton signupButton;
  private boolean launch;

  public StartMenu() {
    super();

    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10));

    this.startButton = addButton(screenComponents, "Start");
    this.loginButton = addButton(screenComponents, "Log In");
    this.signupButton = addButton(screenComponents, "Sign Up");

    this.frame.add(screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
      InteractiveWindow loginMenu = new LoginMenu();
      this.frame.dispose();
    }
    if (e.getSource() == signupButton) {
      InteractiveWindow signupMenu = new SignupMenu();
      this.frame.dispose();
    }
  }

  public void run() {
    while (true) {
      if (this.launch) {
        System.out.println("Window started");
      }
    }
  }
}
