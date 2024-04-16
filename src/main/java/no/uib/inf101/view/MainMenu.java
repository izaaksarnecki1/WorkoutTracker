package no.uib.inf101.view;

import no.uib.inf101.model.User;

import java.awt.event.ActionEvent;

public class MainMenu extends InteractiveWindow {

  private User user;
  public MainMenu(User user) {
    super();
    this.user = user;
    this.frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
