package no.uib.inf101.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends InteractiveWindow {
  private final JButton loginButton;
  private final JButton signupButton;
  private boolean launch;

  public StartMenu() {
    super();

    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10));

    this.loginButton = addButton(screenComponents, "Log In");
    this.signupButton = addButton(screenComponents, "Sign Up");

    this.frame.add(screenComponents);
    this.frame.setVisible(true);
  }

  public JButton getLoginButton() {
    return loginButton;
  }

  public JButton getSignupButton() {
    return signupButton;
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.loginButton.addActionListener(l);
    this.signupButton.addActionListener(l);
  }

  public void run() {
    while (true) {
      if (this.launch) {
        System.out.println("Window started");
      }
    }
  }
}
