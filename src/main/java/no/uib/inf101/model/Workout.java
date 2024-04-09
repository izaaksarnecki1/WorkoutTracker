package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Workout implements IWorkout {

  private LocalDate workoutDate;


  public LocalDate getWorkoutDate() {
    return this.workoutDate;
  }

  public int getWorkoutDateAsInt() {
    return Integer.parseInt(
            this.workoutDate.format(DateTimeFormatter.BASIC_ISO_DATE)
    );
  }

  public void setWorkoutDate(LocalDate workoutDate) {
    this.workoutDate = workoutDate;
  }
}
