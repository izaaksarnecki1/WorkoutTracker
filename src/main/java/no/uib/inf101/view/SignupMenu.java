package no.uib.inf101.view;

import com.google.common.hash.Hashing;
import no.uib.inf101.model.db.Authenticator;
import no.uib.inf101.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class SignupMenu extends InteractiveWindow {
  private final JTextField usernameField;
  private final JPasswordField passwordField;
  private final JButton submitButton;
  public SignupMenu() {
    super();
//    this.screenComponents.setLayout(new GridBagLayout());
    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10)); // try different borders
//    this.screenComponents.setBorder(new StrokeBorder(new BasicStroke()));

    this.usernameField = addTextField(this.screenComponents, "");
    this.passwordField = addPasswordField(this.screenComponents, "");
    this.submitButton = addButton(this.screenComponents, "Submit");
//    this.usernameField.setBounds(new Rectangle(10, 10, 50, 30));
    this.passwordField.setBounds(new Rectangle(10, 50, 50, 30));



    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
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

  @Override
  public String getIdentifier() {
    return "SignupMenu";
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.usernameField.addActionListener(l);
    this.passwordField.addActionListener(l);
    this.submitButton.addActionListener(l);
  }

//  @Override
//  public void actionPerformed(ActionEvent e) {
//    if (e.getSource() == submitButton) {
//      String username = this.usernameField.getText();
//      char[] charPassword = this.passwordField.getPassword();
//      String stringPassword = Hashing
//              .sha256()
//              .hashString(String.valueOf(charPassword), StandardCharsets.UTF_8)
//              .toString();
//      User user = Authenticator.createNewUser(username, stringPassword);
//      if (user == null) {
//        System.err.println("Error making user");
//      }
//    }
//  }
}
