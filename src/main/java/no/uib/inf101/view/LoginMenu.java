package no.uib.inf101.view;

import no.uib.inf101.user.Authenticator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginMenu extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;
  private final Authenticator auth;

  public LoginMenu() {
    super();
    this.auth = new Authenticator();
    this.usernameField = addTextField(this.screenComponents, "Username: ");
    this.passwordField = addPasswordField(this.screenComponents, "Password: ");
    this.submitButton = addButton(this.screenComponents, "Submit");
    this.frame.add(this.screenComponents);
//    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      String username = this.usernameField.getText();
      String password = String.valueOf(this.passwordField.getPassword());
//      try {
//        this.auth.login(username, password);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
      System.out.println(username + " " + password);
    }
  }
  
}
