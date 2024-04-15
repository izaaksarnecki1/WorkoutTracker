package no.uib.inf101;

import no.uib.inf101.db.DatabaseController;
import no.uib.inf101.view.StartMenu;

public class Main {

    public static void main(String[] args) {
      DatabaseController dbController = new DatabaseController();
      StartMenu menu = new StartMenu();
      menu.run();
    }
  }
  