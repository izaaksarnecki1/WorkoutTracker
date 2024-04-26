package no.uib.inf101.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewWorkoutMenu extends InteractiveWindow {

  private final ViewableMenuModel model;
  private JButton backButton;
  private JButton nextPage;
  private JButton prevPage;
  private JButton workout1;
  private JButton workout2;
  private JButton workout3;
  private JButton workout4;
  private JButton workout5;
  private final Map<JButton, Integer> workoutButtons;
  private JButton[] displayButtons = new JButton[5];

  public ViewWorkoutMenu(ViewableMenuModel model) {
    super();
    initiateButtons();
    this.model = model;
    this.workoutButtons = new HashMap<>();
    this.setUpLayout();
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  public JButton getBackButton() {
    return this.backButton;
  }

  public JButton getNextPage() {
    return this.nextPage;
  }

  public JButton getPrevPage() {
    return this.prevPage;
  }

  public JButton getWorkout1() {
    return this.workout1;
  }

  public JButton getWorkout2() {
    return this.workout2;
  }

  public JButton getWorkout3() {
    return this.workout3;
  }

  public JButton getWorkout4() {
    return this.workout4;
  }

  public JButton getWorkout5() {
    return this.workout5;
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.backButton.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {
    ArrayList<ArrayList<String>> workouts = model.getWorkoutData();
    if (workouts == null || workouts.size() == 0) {
      GridBagLayout layout = new GridBagLayout();
      this.screenComponents.setLayout(layout);
      JLabel noWorkoutsLabel = new JLabel("No workouts available.");
      this.screenComponents.add(noWorkoutsLabel);
      this.backButton = addButton(screenComponents, "Back");
      this.screenComponents.add(this.backButton);
    } else {
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(0, 1, 0, 10));
      for (ArrayList<String> workout : workouts) {
        if (workout.size() > 1) {
          // int workoutId = Integer.parseInt(workout.get(0));
          String workoutName = workout.get(1);
          JButton button = new JButton(workoutName);

          buttonPanel.add(button);
        }
      }

      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      backButton = addButton(controlPanel, "Back");

      screenComponents.setLayout(new BorderLayout());
      screenComponents.add(buttonPanel, BorderLayout.CENTER);
      screenComponents.add(controlPanel, BorderLayout.SOUTH);
    }
  }

  private void initiateButtons() {
    
  }
}
