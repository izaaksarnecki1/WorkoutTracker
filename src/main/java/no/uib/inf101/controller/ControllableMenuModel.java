package no.uib.inf101.controller;

import java.awt.event.ActionEvent;

public interface ControllableMenuModel {
  void handleSignupMenu(ActionEvent e);
  void handleLoginMenu(ActionEvent e);
  void handleStartMenu(ActionEvent e);
  void handleMainMenu(ActionEvent e);
}
