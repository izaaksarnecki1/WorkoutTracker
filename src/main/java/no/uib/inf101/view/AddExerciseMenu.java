package no.uib.inf101.view;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import no.uib.inf101.Constants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AddExerciseMenu extends InteractiveWindow implements MenuWithFields {

  private JTextField exerciseNameField;
  private JTextField setsField;
  private JTextField repsField;
  private JTextField weightField;
  private final JLabel exerciseNameLabel = new JLabel();
  private final JLabel setsLabel = new JLabel();
  private final JLabel repsLabel = new JLabel();
  private final JLabel weightLabel = new JLabel();
  private JButton addButton = new JButton();
  private final Map<String, String> fields;

  public AddExerciseMenu() {
    super();
    this.fields = new HashMap<>();
    this.setUpLayout();
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener l) {
    this.exerciseNameField.addActionListener(l);
    this.setsField.addActionListener(l);
    this.repsField.addActionListener(l);
    this.weightField.addActionListener(l);
    this.addButton.addActionListener(l);
  }

  public JButton getAddButton() {
    return this.addButton;
  }

  @Override
  public Map<String, String> getFields() {
    this.fields.put(Constants.ADDEXERCISE_FIELD_EX_NAME, this.exerciseNameField.getText());
    this.fields.put(Constants.ADDEXERCISE_FIELD_SETS, this.setsField.getText());
    this.fields.put(Constants.ADDEXERCISE_FIELD_REPS, this.repsField.getText());
    this.fields.put(Constants.ADDEXERCISE_FIELD_WEIGHT, this.weightField.getText());
    return this.fields;
  }

  @Override
  protected void setUpLayout() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    this.screenComponents.setLayout(layout);

    exerciseNameLabel.setText("Exercise Name: ");
    setsLabel.setText("Sets: ");
    repsLabel.setText("Reps: ");
    weightLabel.setText("Weight (kg): ");

    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(exerciseNameLabel, c);

    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    this.exerciseNameField = addTextField(this.screenComponents, "");
    c.insets = new Insets(10, 0, 0, 10);
    this.screenComponents.add(exerciseNameField, c);

    c.gridx = 0;
    c.gridy = 1;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(setsLabel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.setsField = addTextField(this.screenComponents, "");
    this.screenComponents.add(setsField, c);

    c.gridx = 0;
    c.gridy = 2;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(repsLabel, c);

    c.gridx = 1;
    c.gridy = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.repsField = addTextField(this.screenComponents, "");
    this.screenComponents.add(repsField, c);

    c.gridx = 0;
    c.gridy = 3;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(weightLabel, c);

    c.gridx = 1;
    c.gridy = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.weightField = addTextField(this.screenComponents, "");
    this.screenComponents.add(weightField, c);

    c.gridx = 1;
    c.gridy = 4;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(20, 10, 10, 10);
    this.addButton = addButton(this.screenComponents, "Add");
    this.screenComponents.add(addButton, c);

    this.frame.add(this.screenComponents);
  }
}
