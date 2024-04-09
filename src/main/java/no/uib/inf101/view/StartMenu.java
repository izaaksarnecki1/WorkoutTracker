package no.uib.inf101.view;

import no.uib.inf101.model.Exercise;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends InteractiveWindow  {

  public static final String WINDOW_TITLE = "Workout Tracker";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  private final JFrame frame;
  private final JButton startButton;
  private final JButton loginButton;
  private final JButton signupButton;
  private final JButton addWorkout; // ::::::TEST::::::
  private boolean launch;

  public StartMenu() {
    this.frame = new JFrame();
    this.frame.setTitle(WINDOW_TITLE);
    this.frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
    buttons.setBorder(new EmptyBorder(10, 10, 30, 10));

    this.startButton = this.addButton(buttons, "Start");
    this.loginButton = addButton(buttons, "Log In");
    this.signupButton = addButton(buttons, "Sign Up");
    this.addWorkout = addButton(buttons, "Add Workout"); //TEST

    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.add(buttons);
    this.frame.pack();
    this.frame.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startButton) {
      System.out.println("Start Button Pressed");
    }
    if (e.getSource() == loginButton) {
      System.out.println("log in Button Pressed");
    }
    if (e.getSource() == signupButton) {
      System.out.println("sign up Button Pressed");
    }
    if (e.getSource() == addWorkout) { // TEST
      Exercise exercise = new Exercise("Bench Press", 3, 8, 100);
      System.out.println(exercise);
    } // TEST
    this.launch = true;
  }

  public void run() {
    while (true) {
      if (this.launch) {
        System.out.println("Window started");
      }
    }
  }
}
