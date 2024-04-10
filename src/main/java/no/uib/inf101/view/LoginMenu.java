package no.uib.inf101.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginMenu extends InteractiveWindow {

  private final JTextField username;
  private final JTextField password;

  public LoginMenu() {
    super();

    this.username = addTextField(this.textFields, "Username: ");
    this.password = addTextField(this.textFields, "Password: ");

    this.frame.add(this.textFields);
    this.frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
