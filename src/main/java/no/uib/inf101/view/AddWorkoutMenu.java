package no.uib.inf101.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.uib.inf101.Constants;
import no.uib.inf101.model.Workout;

public class AddWorkoutMenu extends InteractiveWindow implements MenuWithFields {

  private ViewableMenuModel model;
  private JTextField dateField;
  private JTextField workoutNameField;
  private final JLabel dateLabel = new JLabel();
  private final JLabel workoutLabel = new JLabel();
  private JButton addExerciseButton;
  private JButton backButton;
  private JButton saveButton;
  private final Map<String, String> fields;

  public AddWorkoutMenu(ViewableMenuModel model) {
    super();
    this.model = model;
    this.fields = new HashMap<>();
    this.setUpLayout();
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  public JButton getAddExerciseButton() {
    return this.addExerciseButton;
  }

  public JButton getBackButton() {
    return this.backButton;
  }

  @Override
  public Map<String, String> getFields() {
    this.fields.put(Constants.ADDWORKOUT_FIELD_WORKOUTNAME, this.workoutNameField.getText());
    this.fields.put(Constants.ADDWORKOUT_FIELD_DATE, this.dateField.getText());
    return this.fields;
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.saveButton.addActionListener(l);
    this.dateField.addActionListener(l);
    this.workoutNameField.addActionListener(l);
    this.addExerciseButton.addActionListener(l);
    this.backButton.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {

    String date;
    String workoutName;

    if (this.model.workoutExists()) {
      Map<String, String> viewableWorkout = model.getWorkoutDisplay();
      date = viewableWorkout.get(Workout.WORKOUTDATE);
      workoutName = viewableWorkout.get(Workout.WORKOUTNAME);
    } else {
      date = "";
      workoutName = "";
    }

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    this.screenComponents.setLayout(layout);
    dateLabel.setText("Date (YYYY-MM-DD): ");
    workoutLabel.setText("Workout Name: ");

    // Date label and field
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(dateLabel, c);

    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    this.dateField = addTextField(this.screenComponents, date);
    c.insets = new Insets(10, 0, 0, 10);
    this.screenComponents.add(dateField, c);

    // Workout Name label and field
    c.gridx = 0;
    c.gridy = 1;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(workoutLabel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.workoutNameField = addTextField(this.screenComponents, workoutName);
    this.screenComponents.add(workoutNameField, c);

    // Add Exercise button
    c.gridx = 1;
    c.gridy = 2;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0; // Reset weight
    c.insets = new Insets(20, 10, 10, 10);
    this.addExerciseButton = addButton(this.screenComponents, "Add Exercise");
    this.screenComponents.add(addExerciseButton, c);

    // Back button
    c.gridx = 1;
    c.gridy = 3;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0; // Reset weight
    c.insets = new Insets(10, 10, 10, 10);
    this.backButton = addButton(this.screenComponents, "Back");
    this.screenComponents.add(backButton, c);

    // Save button
    c.gridx = 1;
    c.gridy = 4;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0; // Reset weight
    c.insets = new Insets(10, 10, 10, 10);
    this.saveButton = addButton(this.screenComponents, "Save");
    this.screenComponents.add(saveButton, c);

    this.frame.add(this.screenComponents);
  }
}
