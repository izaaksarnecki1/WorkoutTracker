package no.uib.inf101.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginMenu extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;

  public LoginMenu() {
    super();
    this.usernameField = addTextField(this.screenComponents, "Username: ");
    this.passwordField = addPasswordField(this.screenComponents, "Password: ");
    this.submitButton = addButton(this.screenComponents, "Submit");
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      String username = this.usernameField.getText();
      String password = String.valueOf(this.passwordField.getPassword());

      // Code here

      System.out.println(username + " " + password);
    }
  }
}
