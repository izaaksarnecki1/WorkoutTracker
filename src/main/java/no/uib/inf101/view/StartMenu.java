package no.uib.inf101.view;

import no.uib.inf101.model.Exercise;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu implements ActionListener {

  public static final String WINDOW_TITLE = "Workout Tracker";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  private final JFrame frame;
  private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 30);
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

    this.startButton = addButton(buttons, "Start");
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

  private JButton addButton(JPanel buttons, String text) {
    return this.addButton(buttons, text, null);
  }

  private JButton addButton(JPanel buttons, String text, Dimension size) {
    return this.addButton(buttons, text, size, null, null);
  }

  private JButton addButton(JPanel buttons, String text, Dimension size, Integer xPadding, Integer yPadding) {
    JButton button = new JButton();

    if (size != null) {
      button.setSize(size);
    }

    final Dimension paddingBoxDimension;

    if (xPadding != null && yPadding != null) {
      paddingBoxDimension = new Dimension(xPadding, yPadding);
    } else {
      paddingBoxDimension = new Dimension(20, 20);
    }

    button.setText(text);
    button.setFont(BUTTON_FONT);
    button.addActionListener(this);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    buttons.add(Box.createRigidArea(paddingBoxDimension));
    buttons.add(button);
    return button;
  }
}
