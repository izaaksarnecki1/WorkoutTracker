package no.uib.inf101;

import no.uib.inf101.controller.ButtonController;
import no.uib.inf101.controller.ControllableMenuModel;
import no.uib.inf101.model.MenuModel;
import no.uib.inf101.model.db.DatabaseController;
import no.uib.inf101.view.StartMenu;

public class Main {

    public static void main(String[] args) {
      new DatabaseController();
      StartMenu menu = new StartMenu();
      ControllableMenuModel model = new MenuModel();
      new ButtonController(model, menu);
    }
  }
  