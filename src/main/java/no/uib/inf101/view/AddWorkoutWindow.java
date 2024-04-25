package no.uib.inf101.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddWorkoutWindow extends InteractiveWindow {

  private JTextField dateField;
  private final JLabel dateLabel = new JLabel();
  private JButton addExerciseButton;
  private JButton backButton;
  private final Map<String, String> fields;
  private final ViewableMenuModel model;

  public AddWorkoutWindow(ViewableMenuModel model) {
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
  public void addActionListener(ActionListener l) {
    this.dateField.addActionListener(l);
    this.addExerciseButton.addActionListener(l);
    this.backButton.addActionListener(l);
  }

  @Override
  protected void setUpLayout() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    this.screenComponents.setLayout(layout);
    

  }

}
