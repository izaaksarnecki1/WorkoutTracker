package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

  private void setUpLayout() {
    this.screenComponents.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    this.addWorkoutButton = addButton(this.screenComponents, "+");
    this.viewWorkoutsButton = addButton(this.screenComponents, "View Workouts");
    this.editUserButton = addButton(this.screenComponents, "Edit Profile");
    this.frame.add(this.screenComponents);
  }
}
