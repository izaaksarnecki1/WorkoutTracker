package no.uib.inf101.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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
  public void addActionListener(ActionListener l) {
    this.usernameField.addActionListener(l);
    this.passwordField.addActionListener(l);
    this.submitButton.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {

  }
}
