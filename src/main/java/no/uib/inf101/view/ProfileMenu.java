package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileMenu extends InteractiveWindow {

  private JTextField firstNameField;
  private JTextField lastNameField;
  private JTextField weightField;
  private JTextField heightField;
  private JButton saveButton;
  private final JLabel firstNameLabel = new JLabel();
  private final JLabel lastNameLabel = new JLabel();
  private final JLabel weightLabel = new JLabel();
  private final JLabel heightLabel = new JLabel();

  public ProfileMenu() {
    super();
    this.setUpLayout();
    this.frame.add(this.screenComponents);
    this.frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener l) {

  }

  @Override
  protected void setUpLayout() {
    // Used some ChatGPT to help me with the layout.
    // "Using GridBagLayout, how would I set up JTextFields with labels?"

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    this.screenComponents.setLayout(layout);

    firstNameLabel.setText("First name:");
    lastNameLabel.setText("Last name:");
    weightLabel.setText("Weight (kg): ");
    heightLabel.setText("Height (cm): ");


    // First name label and field
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(firstNameLabel, c);

    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    this.firstNameField = addTextField(this.screenComponents, "");
    c.insets = new Insets(10, 0, 0, 10);
    this.screenComponents.add(firstNameField, c);

    // Last name label and field
    c.gridx = 0;
    c.gridy = 1;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(lastNameLabel, c);

    c.gridx = 1;
    c.gridy = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.lastNameField = addTextField(this.screenComponents, "");
    this.screenComponents.add(lastNameField, c);

    // Weight label and field
    c.gridx = 0;
    c.gridy = 2;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0;
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(weightLabel, c);

    c.gridx = 1;
    c.gridy = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.weightField = addTextField(this.screenComponents, "");
    this.screenComponents.add(weightField, c);

    // Height label and field
    c.gridx = 0;
    c.gridy = 3;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0; // Reset weight
    c.insets = new Insets(10, 10, 0, 10);
    this.screenComponents.add(heightLabel, c);

    c.gridx = 1;
    c.gridy = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    c.insets = new Insets(10, 0, 0, 10);
    this.heightField = addTextField(this.screenComponents, "");
    this.screenComponents.add(heightField, c);

    // Save button
    c.gridx = 1;
    c.gridy = 4;
    c.anchor = GridBagConstraints.PAGE_END;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.0; // Reset weight
    c.insets = new Insets(20, 10, 10, 10);
    this.saveButton = addButton(this.screenComponents, "Save");
    this.screenComponents.add(saveButton, c);

    this.frame.add(this.screenComponents);
  }
}
