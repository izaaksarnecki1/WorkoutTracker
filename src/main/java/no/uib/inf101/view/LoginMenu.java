package no.uib.inf101.view;

import javax.swing.*;

import no.uib.inf101.Constants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The LoginMenu class represents a graphical user interface for the login menu.
 * It extends the InteractiveWindow class and implements the MenuWithFields interface.
 * The LoginMenu allows users to enter their username and password and provides buttons for submitting the login information and navigating back.
 */
public class LoginMenu extends InteractiveWindow implements MenuWithFields {

  private JTextField usernameField;
  private JPasswordField passwordField;
  private final JLabel usernameLabel = new JLabel();
  private final JLabel passwordLabel = new JLabel();
  private JButton submitButton;
  private JButton backButton;
  private final Map<String, String> fields;

  public LoginMenu() {
    super();
    this.fields = new HashMap<>();
    this.setUpLayout(); 
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.usernameField.addActionListener(l);
    this.passwordField.addActionListener(l);
    this.submitButton.addActionListener(l);
    this.backButton.addActionListener(l);
  }

  @Override
  public Map<String, String> getFields() {
    this.fields.put(Constants.LOGINMENU_FIELD_USERNAME, this.usernameField.getText());
    this.fields.put(Constants.LOGINMENU_FIELD_PASSWORD, new String(this.passwordField.getPassword()));
    return this.fields;
  }

  public JButton getSubmitButton() {
    return this.submitButton;
  }

  public JButton getBackButton() {
    return this.backButton;
  }

  @Override
  protected void setUpLayout() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    this.screenComponents.setLayout(layout);

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets(10, 10, 0, 10);
    this.usernameLabel.setText("Username:");
    this.screenComponents.add(usernameLabel, c);

    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    this.usernameField = addTextField(this.screenComponents, "");
    c.insets = new Insets(10, 0, 0, 10);
    this.screenComponents.add(usernameField, c);

    c.gridx = 0;
    c.gridy = 1;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.passwordLabel.setText("Password:");
    this.screenComponents.add(passwordLabel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.passwordField = addPasswordField(this.screenComponents, "");
    this.screenComponents.add(passwordField, c);

    c.gridx = 1;
    c.gridy = 2;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(20, 10, 10, 10);
    this.submitButton = addButton(this.screenComponents, "Submit");
    this.screenComponents.add(submitButton, c);

    c.gridx = 1;
    c.gridy = 3;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 10, 10);
    this.backButton = addButton(this.screenComponents, "Back");
    this.screenComponents.add(backButton, c);
  }
}
