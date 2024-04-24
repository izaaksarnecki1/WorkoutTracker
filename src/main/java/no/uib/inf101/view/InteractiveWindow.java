package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class InteractiveWindow {

  public static final String WINDOW_TITLE = "Workout Tracker";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 30);
  private final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
  final JFrame frame;
  JPanel screenComponents;
  public InteractiveWindow() {
    this.frame = new JFrame();
    this.frame.setTitle(WINDOW_TITLE);
    this.frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.screenComponents = new JPanel();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.pack();
    this.setPreferredScreenLocation();
  }

  JButton addButton(JPanel buttons, String text) {
    return this.addButton(buttons, text, null);
  }

  JButton addButton(JPanel buttons, String text, Dimension size) {
    return this.addButton(buttons, text, size, null, null);
  }

  JButton addButton(JPanel buttons, String text, Dimension size, Integer xPadding, Integer yPadding) {
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
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    buttons.add(Box.createRigidArea(paddingBoxDimension));
    buttons.add(button);
    return button;
  }

  JTextField addTextField(JPanel textFields, String text) {
    // TODO: consider using setbounds for fields instead of layouts
    JTextField textField = new JTextField();
    textField.setText(text);
    textField.setFont(TEXT_FONT);
//    textField.setAlignmentX(Component.CENTER_ALIGNMENT);
    textField.setBounds(new Rectangle(new Dimension(20, 50) ));
    textFields.add(Box.createRigidArea(new Dimension(20, 20)));
    textFields.add(textField);
    return textField;
  }

  JPasswordField addPasswordField(JPanel textFields, String text) {
    // textFields param is used to simplify display process.
    JPasswordField passwordField = new JPasswordField();
    passwordField.setText(text);
//    passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFields.add(Box.createRigidArea(new Dimension(20, 20)));
    textFields.add(passwordField);
    return passwordField;
  }

  public void dispose() {
    this.frame.dispose();
  }

  private void setPreferredScreenLocation() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) ((screenSize.getWidth() - SCREEN_WIDTH) / 2);
    int centerY = (int) ((screenSize.getHeight() - SCREEN_HEIGHT) / 2);
    this.frame.setLocation(centerX, centerY);
  }

  public abstract void addActionListener(ActionListener l);
  protected abstract void setUpLayout();
}
