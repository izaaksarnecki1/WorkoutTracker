package no.uib.inf101.view;

import com.google.common.hash.Hashing;
import no.uib.inf101.db.DatabaseController;
import no.uib.inf101.model.user.Authenticator;
import no.uib.inf101.model.user.User;

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
      String password = Hashing
              .sha256()
              .hashString(String.valueOf(this.passwordField.getPassword()), StandardCharsets.UTF_8)
              .toString();
//      if (Authenticator.authUser(username, password)) {

//        int id = Integer.parseInt(DatabaseController.fetchUserId(username));
//        User user = new User(username, password, id);
//      }
        if (!Authenticator.authUser(username, password)) {
          System.out.println("user not found");
        }
    }
  }
}
