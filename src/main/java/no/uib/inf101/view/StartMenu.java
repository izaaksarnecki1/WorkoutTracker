package no.uib.inf101.view;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * The StartMenu class represents the start menu of the workout tracker application.
 * It extends the InteractiveWindow class and provides functionality for displaying
 * login and signup buttons.
 */
public class StartMenu extends InteractiveWindow {
  private JButton loginButton;
  private JButton signupButton;

  public StartMenu() {
    super();
    this.setUpLayout();

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

  protected void setUpLayout() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    screenComponents.setLayout(layout);

    // Login button
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.CENTER;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(10, 10, 0, 10);
    loginButton = addButton(screenComponents, "Login");
    screenComponents.add(loginButton, c);

    // Signup button
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(10, 10, 0, 10);
    signupButton = addButton(screenComponents, "Signup");
    screenComponents.add(signupButton, c);
  }
}
