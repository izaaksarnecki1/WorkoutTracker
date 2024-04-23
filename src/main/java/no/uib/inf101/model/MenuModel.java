package no.uib.inf101.model;

import com.google.common.hash.Hashing;
import no.uib.inf101.Constants;
import no.uib.inf101.controller.ControllableMenuModel;
import no.uib.inf101.model.db.Authenticator;
import no.uib.inf101.view.*;

import java.nio.charset.StandardCharsets;

public class MenuModel implements ControllableMenuModel, ViewableMenuModel {

  private User user = null;
  public MenuModel() {
  }


  @Override
  public InteractiveWindow handleSignupMenu(String identifier, String uname, char[] password) {
    if (identifier.equals(Constants.SIGNUPMENU_SUBMIT)) {
      String stringPassword = Hashing
        .sha256()
        .hashString(String.valueOf(password), StandardCharsets.UTF_8)
        .toString();
      User user = Authenticator.createNewUser(uname, stringPassword);
      if (user != null) {
        this.user = user;
        return new MainMenu();
      } else {
        System.err.println("Error creating user. ");
      }
    }
    return null;
  }

  @Override
  public InteractiveWindow handleLoginMenu(String identifier, String uname, char[] password) {
    String stringPassword = Hashing
            .sha256()
            .hashString(String.valueOf(password), StandardCharsets.UTF_8)
            .toString();
    User user = Authenticator.loginUser(uname, stringPassword);
    if (user != null) {
      this.user = user;
      return new MainMenu();
    } else {
      System.err.println("Error logging in user. ");
    }
    return null;
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
  public void handleMainMenu(String identifier) {

  }
}
