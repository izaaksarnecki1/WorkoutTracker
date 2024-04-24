package no.uib.inf101.controller;

import no.uib.inf101.view.InteractiveWindow;

import java.util.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public interface ControllableMenuModel {
  InteractiveWindow handleSignupMenu(String identifier, String uname, char[] pass);
  InteractiveWindow handleLoginMenu(String identifier, String uname, char[] pass);
  InteractiveWindow handleStartMenu(String identifier);
  InteractiveWindow handleMainMenu(String identifier);
  InteractiveWindow handleProfileMenu(String identifier, Map<String, String>  fields);
}
