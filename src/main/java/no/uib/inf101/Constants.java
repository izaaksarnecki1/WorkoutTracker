package no.uib.inf101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
  public static final String STARTMENU_BUTTON_LOGIN = "login";
  public static final String STARTMENU_BUTTON_SIGNUP = "signup";
  public static final String SIGNUPMENU_BUTTON_SUBMIT = "submit";
  public static final String LOGINMENU_BUTTON_SUBMIT = "submit";
  public static final String MAINMENU_BUTTON_ADDWORKOUT = "add_workout";
  public static final String MAINMENU_BUTTON_EDITUSER = "edit_user";
  public static final String MAINMENU_BUTTON_VIEWWORKOUTS = "view_workouts";
  public static final String PROFILEMENU_FIELD_FIRST = "first";
  public static final String PROFILEMENU_FIELD_LAST = "last";
  public static final String PROFILEMENU_FIELD_WEIGHT = "weight";
  public static final String PROFILEMENU_FIELD_HEIGHT = "height";
  private static final ArrayList<String> strings = new ArrayList<>(Arrays.asList(PROFILEMENU_FIELD_FIRST, PROFILEMENU_FIELD_LAST, PROFILEMENU_FIELD_WEIGHT, PROFILEMENU_FIELD_HEIGHT));
  public static final List<String> PROFILE_MENU_ATTR = strings;
  public static final String PROFILEMENU_BUTTON_SAVE = "save";
  }
