package no.uib.inf101;

import no.uib.inf101.model.Exercise;
import no.uib.inf101.model.IWorkout;
import no.uib.inf101.model.Workout;
import no.uib.inf101.model.WorkoutExercise;
import no.uib.inf101.view.MainMenu;

import java.time.LocalDate;
import java.util.Iterator;


public class Main {

    public static void main(String[] args) {
//      Exercise ex1 = new Exercise("test", 1, 1, 1);
//      Exercise ex2 = new Exercise("test2", 2, 2, 2);
//
//      LocalDate date = LocalDate.now();
//
//      IWorkout w1 = new Workout(date);
//      IWorkout w2 = new Workout(date);


      MainMenu menu = new MainMenu();
      menu.run();
    }
  }
  