package no.uib.inf101.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The `InteractiveWindow` class represents an abstract window for interactive
 * user interfaces.
 * It provides methods for adding buttons, text fields, and password fields to
 * the window.
 * Subclasses of `InteractiveWindow` must implement the `addActionListener` and
 * `setUpLayout` methods.
 */
public abstract class InteractiveWindow {

  public static final String WINDOW_TITLE = "Workout Tracker";
  public static final int SCREEN_WIDTH = 800;
  public static final int SCREEN_HEIGHT = 600;

  private final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 30);
  private final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
  final JFrame frame;
  JPanel screenComponents;

  /**
   * Constructs a new InteractiveWindow object.
   * It initializes the JFrame, sets the window title, dimensions, and default
   * close operation.
   * It also creates a JPanel to hold the screen components and sets the preferred
   * screen location.
   */
  public InteractiveWindow() {
    this.frame = new JFrame();
    this.frame.setTitle(WINDOW_TITLE);
    this.frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.screenComponents = new JPanel();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.pack();
    this.setPreferredScreenLocation();
  }

  /**
   * Adds a button to the specified JPanel with the given text.
   * 
   * @param buttons The JPanel to add the button to.
   * @param text    The text to be displayed on the button.
   * @return The created JButton object.
   */
  JButton addButton(JPanel buttons, String text) {
    JButton button = new JButton();
    button.setText(text);
    button.setFont(BUTTON_FONT);
    buttons.add(Box.createRigidArea(new Dimension(20, 20)));
    buttons.add(button);
    return button;
  }

  /**
   * Adds a text field to the specified JPanel with the given text.
   * 
   * @param textFields The JPanel to add the text field to.
   * @param text       The text to be displayed in the text field.
   * @return The created JTextField object.
   */
  JTextField addTextField(JPanel textFields, String text) {
    JTextField textField = new JTextField();
    textField.setText(text);
    textField.setFont(TEXT_FONT);
    textField.setBounds(new Rectangle(new Dimension(20, 50)));
    textFields.add(Box.createRigidArea(new Dimension(20, 20)));
    textFields.add(textField);
    return textField;
  }

  /**
   * Adds a password field to the specified JPanel with the given text.
   * 
   * @param textFields The JPanel to add the password field to.
   * @param text       The text to be displayed in the password field.
   * @return The created JPasswordField object.
   */
  JPasswordField addPasswordField(JPanel textFields, String text) {
    JPasswordField passwordField = new JPasswordField();
    passwordField.setText(text);
    textFields.add(Box.createRigidArea(new Dimension(20, 20)));
    textFields.add(passwordField);
    return passwordField;
  }

  /**
   * Disposes the JFrame, releasing any resources used by it.
   */
  public void dispose() {
    this.frame.dispose();
  }

  private void setPreferredScreenLocation() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) ((screenSize.getWidth() - SCREEN_WIDTH) / 2);
    int centerY = (int) ((screenSize.getHeight() - SCREEN_HEIGHT) / 2);
    this.frame.setLocation(centerX, centerY);
  }

  /**
   * Adds an ActionListener to the interactive components in the window.
   * 
   * @param l The ActionListener to be added.
   */
  public abstract void addActionListener(ActionListener l);

  /**
   * Sets up the layout of the interactive components in the window.
   * This method should be implemented by subclasses.
   */
  protected abstract void setUpLayout();
}
