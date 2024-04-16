package no.uib.inf101.view;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.db.Authenticator;
import no.uib.inf101.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;

public class SignupMenu extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;

  public SignupMenu() {
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
      char[] charPassword = this.passwordField.getPassword();
      String stringPassword = Hashing
              .sha256()
              .hashString(String.valueOf(charPassword), StandardCharsets.UTF_8)
              .toString();
      User user = Authenticator.createNewUser(username, stringPassword);

    }
  }
}
