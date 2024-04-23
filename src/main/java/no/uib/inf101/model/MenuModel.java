package no.uib.inf101.model;

import no.uib.inf101.Constants;
import no.uib.inf101.controller.ControllableMenuModel;
import no.uib.inf101.view.InteractiveWindow;
import no.uib.inf101.view.LoginMenu;
import no.uib.inf101.view.SignupMenu;

import java.awt.event.ActionEvent;

public class MenuModel implements ControllableMenuModel {

  private User user;
  public MenuModel() {
  }


  @Override
  public void handleSignupMenu(ActionEvent e) {
//    if (e.getSource() )
  }

  @Override
  public void handleLoginMenu(ActionEvent e) {

  }

  @Override
  public InteractiveWindow handleStartMenu(String identifier) {
    if (identifier.equals(Constants.STARTMENU_SIGNUP)) {
      return new SignupMenu();
    } else if (identifier.equals(Constants.STARTMENU_LOGIN)) {
      return new LoginMenu();
    }
    return null;
  }

  @Override
  public void handleMainMenu(ActionEvent e) {

  }
}
