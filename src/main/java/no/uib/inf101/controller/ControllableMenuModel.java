package no.uib.inf101.controller;

import no.uib.inf101.view.InteractiveWindow;

import java.awt.event.ActionEvent;

public interface ControllableMenuModel {
  InteractiveWindow handleSignupMenu(String identifier, String uname, char[] pass);
  InteractiveWindow handleLoginMenu(String identifier, String uname, char[] pass);
  InteractiveWindow handleStartMenu(String identifier);
  InteractiveWindow handleMainMenu(String identifier);
}
