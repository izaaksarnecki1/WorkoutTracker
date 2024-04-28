package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MainMenu class represents the main menu of the workout tracker application.
 * It extends the InteractiveWindow class and provides buttons for adding a workout,
 * editing the user profile, and viewing workouts.
 */
public class MainMenu extends InteractiveWindow {

  private JButton addWorkoutButton;
  private JButton editUserButton;
  private JButton viewWorkoutsButton;


  public MainMenu() {
    super();
    this.setUpLayout();
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  public JButton getAddWorkoutButton() {
    return addWorkoutButton;
  }

  public JButton getEditUserButton() {
    return editUserButton;
  }

  public JButton getViewWorkoutsButton() {
    return viewWorkoutsButton;
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.addWorkoutButton.addActionListener(l);
    this.editUserButton.addActionListener(l);
    this.viewWorkoutsButton.addActionListener(l);
  }

  protected void setUpLayout() {
    this.screenComponents.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(10, 10, 10, 10);
    this.addWorkoutButton = addButton(this.screenComponents, "+");

    c.gridx = 0;
    c.gridy = 1;
    this.viewWorkoutsButton = addButton(this.screenComponents, "View Workouts");

    c.gridx = 0;
    c.gridy = 2;
    this.editUserButton = addButton(this.screenComponents, "Edit Profile");

    this.frame.add(this.screenComponents);
  }
}
