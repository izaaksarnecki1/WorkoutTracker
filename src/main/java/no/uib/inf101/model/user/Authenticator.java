package no.uib.inf101.model.user;

import no.uib.inf101.db.DatabaseController;
import no.uib.inf101.model.Exercise;
import no.uib.inf101.model.Workout;

import java.time.LocalDate;

public class Authenticator {
  private final DatabaseController databaseController;

  public Authenticator() {
    LocalDate date = LocalDate.now();
    User user = new User("amalie", "test");
    Workout workout = new Workout(date);
    Exercise exercise = new Exercise("Bench press", 10,10,10);
    Exercise exercise2 = new Exercise("Pull up", 20,20);
    workout.addExercise(exercise);
    workout.addExercise(exercise2);

    user.addWorkout(workout);

    this.databaseController = new DatabaseController();
//    this.databaseController.addUser(user);
//    this.databaseController.converterTest(workout);
  }
}
