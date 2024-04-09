package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class InteractiveWindow implements ActionListener {

  public static final String WINDOW_TITLE = "Workout Tracker";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;
  private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 30);
  final JFrame frame;
  JPanel buttons;

  public InteractiveWindow() {
    this.frame = new JFrame();
    this.frame.setTitle(WINDOW_TITLE);
    this.frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.buttons = new JPanel();
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
    button.addActionListener(this);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    buttons.add(Box.createRigidArea(paddingBoxDimension));
    buttons.add(button);
    return button;
  }
}