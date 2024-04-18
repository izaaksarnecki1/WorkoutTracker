package no.uib.inf101;

import no.uib.inf101.controller.ButtonController;
import no.uib.inf101.model.db.DatabaseController;
import no.uib.inf101.view.StartMenu;

public class Main {

    public static void main(String[] args) {
      DatabaseController dbController = new DatabaseController();
      StartMenu menu = new StartMenu();

      new ButtonController(menu);

      menu.run();
    }
  }
  