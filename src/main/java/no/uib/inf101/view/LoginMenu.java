package no.uib.inf101.view;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.User;
import no.uib.inf101.model.db.Authenticator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class LoginMenu extends InteractiveWindow {

  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;

  public LoginMenu() {
    super();
    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10)); // try different borders
    this.usernameField = addTextField(this.screenComponents, "");
    this.passwordField = addPasswordField(this.screenComponents, "");
    this.submitButton = addButton(this.screenComponents, "Submit");
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.usernameField.addActionListener(l);
    this.passwordField.addActionListener(l);
    this.submitButton.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {

  }

  public JTextField getUsernameField() {
    return usernameField;
  }

  public JPasswordField getPasswordField() {
    return passwordField;
  }

  public JButton getSubmitButton() {
    return submitButton;
  }
}
