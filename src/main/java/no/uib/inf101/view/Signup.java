package no.uib.inf101.view;

import no.uib.inf101.model.user.Authenticator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Signup extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;

  public Signup() {
    super();
    this.usernameField = addTextField(this.screenComponents, "");
    this.passwordField = addPasswordField(this.screenComponents, "");
    this.submitButton = addButton(this.screenComponents, "Submit");
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      String username = this.usernameField.getText();
      String password = String.valueOf(this.passwordField.getPassword());
      if (!Authenticator.checkUsername(username)) {

      }
    }
  }
}
