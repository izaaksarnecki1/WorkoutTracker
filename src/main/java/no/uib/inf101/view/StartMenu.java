package no.uib.inf101.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class StartMenu extends InteractiveWindow  {

  private final JButton startButton;
  private final JButton loginButton;
  private final JButton signupButton;
  private boolean launch;

  public StartMenu() {
    super();

    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
    buttons.setBorder(new EmptyBorder(10, 10, 30, 10));

    this.startButton = addButton(buttons, "Start");
    this.loginButton = addButton(buttons, "Log In");
    this.signupButton = addButton(buttons, "Sign Up");

    this.frame.add(buttons);
    this.frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
      InteractiveWindow mainMenu = new MainMenu();
      this.frame.setVisible(false);
      this.frame.removeAll();
    }
      if (e.getSource() == loginButton) {
      System.out.println("log in Button Pressed");
    }
    if (e.getSource() == signupButton) {
      System.out.println("sign up Button Pressed");
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
