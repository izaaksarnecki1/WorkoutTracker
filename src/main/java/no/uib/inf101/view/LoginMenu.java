package no.uib.inf101.view;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.User;
import no.uib.inf101.model.db.Authenticator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;

public class LoginMenu extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;

  public LoginMenu() {
    super();
    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10)); // try different borders
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
      char[] charPassword = this.passwordField.getPassword();
      String stringPassword = Hashing
              .sha256()
              .hashString(String.valueOf(charPassword), StandardCharsets.UTF_8)
              .toString();
      User user = Authenticator.loginUser(username, stringPassword);
      if (user == null) {
        System.err.println("Error logging in. ");
      } 
    }
  }
}
