package no.uib.inf101.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends InteractiveWindow {
  private final JButton loginButton;
  private final JButton signupButton;
  private boolean launch;

  public StartMenu() {
    super();

    this.screenComponents.setLayout(new BoxLayout(screenComponents, BoxLayout.Y_AXIS));
    this.screenComponents.setBorder(new EmptyBorder(10, 10, 30, 10));

    this.loginButton = addButton(screenComponents, "Log In");
    this.signupButton = addButton(screenComponents, "Sign Up");

    this.frame.add(screenComponents);
    this.frame.setVisible(true);

    this.compList[0] = loginButton;
    this.compList[1] = signupButton;

    this.buttonMap.put("loginButton", this.loginButton);
    this.buttonMap.put("signupButton", this.signupButton);
  }

  public JButton getLoginButton() {
    return loginButton;
  }

  public JButton getSignupButton() {
    return signupButton;
  }

  @Override
  public String getIdentifier() {
    return "StartMenu";
  }

  @Override
  protected void addActionListener(ActionListener l) {
    this.loginButton.addActionListener(l);
    this.signupButton.addActionListener(l);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
      InteractiveWindow loginMenu = new LoginMenu();
      this.frame.dispose();
    }
    if (e.getSource() == signupButton) {
      InteractiveWindow signupMenu = new SignupMenu();
      this.frame.dispose();
    }
  }

  public void run() {
    while (true) {
      if (this.launch) {
        System.out.println("Window started");
      }
    }
  }
}
