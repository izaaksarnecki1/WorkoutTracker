package no.uib.inf101.view;

import no.uib.inf101.model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends InteractiveWindow {

  public MainMenu() {
    super();
    this.frame.setVisible(true);
  }


  @Override
  public String getIdentifier() {
    return "MainMenu";
  }

  @Override
  public void addActionListener(ActionListener l) {

  }
}
