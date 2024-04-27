package no.uib.inf101.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The ViewWorkoutMenu class represents a menu for viewing workout details.
 * It extends the InteractiveWindow class and provides methods to retrieve
 * buttons and set up the layout of the menu.
 */
public class ViewWorkoutMenu extends InteractiveWindow {

  private final ViewableMenuModel model;
  private JButton backButton;
  private JButton nextPage;
  private JButton prevPage;
  private final Font textBoxFont = new Font("Arial", Font.PLAIN, 20);

  /**
   * Constructs a ViewWorkoutMenu object with the specified ViewableMenuModel.
   * It sets up the layout and adds the screen components to the frame.
   *
   * @param model the ViewableMenuModel to use for retrieving workout data
   */
  public ViewWorkoutMenu(ViewableMenuModel model) {
    super();
    this.model = model;
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

  @Override
  public void addActionListener(ActionListener l) {
    this.backButton.addActionListener(l);
    this.nextPage.addActionListener(l);
    this.prevPage.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {
    ArrayList<ArrayList<String>> workouts = model.getWorkoutData();
    ArrayList<String> workout = model.getCurrentWorkout();
    if (workouts == null || workouts.size() == 0) {
      GridBagLayout layout = new GridBagLayout();
      this.screenComponents.setLayout(layout);
      JLabel noWorkoutsLabel = new JLabel("No workouts available.");
      this.screenComponents.add(noWorkoutsLabel);
      this.backButton = addButton(screenComponents, "Back");
      this.screenComponents.add(this.backButton);
    } else {
      JTextArea workoutTextArea = createWorkoutTextArea(workout);
      this.screenComponents.setLayout(new BorderLayout());
      this.screenComponents.add(workoutTextArea, BorderLayout.CENTER);
    }

    JPanel navPanel = new JPanel();
    navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.X_AXIS));
    navPanel.add(Box.createHorizontalGlue());
    this.prevPage = addButton(navPanel, "<");
    this.backButton = addButton(navPanel, "Back");
    this.nextPage = addButton(navPanel, ">");
    navPanel.add(this.prevPage);
    navPanel.add(this.backButton);
    navPanel.add(this.nextPage);
    navPanel.add(Box.createHorizontalGlue());

    this.screenComponents.add(navPanel, BorderLayout.SOUTH);
  }

  private JTextArea createWorkoutTextArea(ArrayList<String> workout) {
    JTextArea workoutTextArea = new JTextArea();
    workoutTextArea.setFont(textBoxFont);
    workoutTextArea.setEditable(false);
    workoutTextArea.append("Workout: " + workout.get(1) + "\n");
    workoutTextArea.append("Date: " + workout.get(2) + "\n");
    workoutTextArea.append("\n");
    workoutTextArea.append("Exercises: \n");
    ArrayList<ArrayList<String>> exercises = model.getExerciseData(Integer.parseInt(workout.get(0)));
    for (ArrayList<String> exercise : exercises) {
      workoutTextArea.append(exercise.get(1) + " - Sets: " + exercise.get(2) + " Reps: " + exercise.get(3) + " Weight: " + exercise.get(4) + "\n");
    }
    return workoutTextArea;
  }
}
