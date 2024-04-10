package no.uib.inf101.user;

import no.uib.inf101.db.DatabaseController;

public class Authenticator {
  private final DatabaseController databaseController;

  public Authenticator() {
    this.databaseController = new DatabaseController();
  }
}
