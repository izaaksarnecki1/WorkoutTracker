package no.uib.inf101.model;

import com.google.common.hash.Hashing;
import no.uib.inf101.Constants;
import no.uib.inf101.controller.ControllableMenuModel;
import no.uib.inf101.model.db.Authenticator;
import no.uib.inf101.model.db.DatabaseController;
import no.uib.inf101.view.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MenuModel implements ControllableMenuModel, ViewableMenuModel {

  private User user = null;
  public MenuModel() {
  }

  @Override
  public InteractiveWindow handleSignupMenu(String identifier, String uname, char[] password) {
    if (identifier.equals(Constants.SIGNUPMENU_BUTTON_SUBMIT)) {
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
      setUserDbAttributes();
      return new MainMenu();
    } else {
      System.err.println("Error logging in user. ");
    }
    return null;
  }

  @Override
  public InteractiveWindow handleStartMenu(String identifier) {
    if (identifier.equals(Constants.STARTMENU_BUTTON_SIGNUP)) {
      return new SignupMenu();
    } else if (identifier.equals(Constants.STARTMENU_BUTTON_LOGIN)) {
      return new LoginMenu();
    }
    return null;
  }

  @Override
  public InteractiveWindow handleMainMenu(String identifier) {
    if (identifier.equals(Constants.MAINMENU_BUTTON_EDITUSER)) {
      return new ProfileMenu(this);
    }
    return null;
  }

  @Override
  public InteractiveWindow handleProfileMenu(String identifier, Map<String, String> fields) {
    if (identifier.equals(Constants.PROFILEMENU_BUTTON_SAVE)) {
      user.setFirstName(fields.get(Constants.PROFILEMENU_FIELD_FIRST));
      user.setLastName(fields.get(Constants.PROFILEMENU_FIELD_LAST));
      user.setWeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_WEIGHT)));
      user.setHeight(Integer.parseInt(fields.get(Constants.PROFILEMENU_FIELD_HEIGHT)));
      DatabaseController.updateRow(user);
      return new MainMenu();
    }
    return null;
  }

  @Override
  public Map<String, String> getUserProfile() {
    Map<String, String> profileData = new HashMap<>();
    profileData.put(User.FIRST_NAME, this.user.getFirstName());
    profileData.put(User.LAST_NAME, this.user.getLastName());
    profileData.put(User.WEIGHT, String.valueOf(this.user.getWeight()));
    profileData.put(User.HEIGHT, String.valueOf(this.user.getHeight()));
    return profileData;
  }

  private void setUserDbAttributes() {
    Map<String, String> userAttributes = DatabaseController.getRow(user);
    System.out.println(userAttributes);
    user.setFirstName(userAttributes.get(User.FIRST_NAME));
    user.setLastName(userAttributes.get(User.LAST_NAME));
    user.setWeight(Integer.parseInt(userAttributes.get(User.WEIGHT)));
    user.setHeight(Integer.parseInt(userAttributes.get(User.HEIGHT)));
  }
}
