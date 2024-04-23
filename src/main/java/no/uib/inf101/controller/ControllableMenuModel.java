package no.uib.inf101.controller;

import no.uib.inf101.view.InteractiveWindow;

import java.awt.event.ActionEvent;

public interface ControllableMenuModel {
  void handleSignupMenu(ActionEvent e);
  void handleLoginMenu(ActionEvent e);
  InteractiveWindow handleStartMenu(String identifier);
  void handleMainMenu(ActionEvent e);
}
