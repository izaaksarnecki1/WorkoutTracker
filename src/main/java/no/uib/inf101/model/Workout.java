package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Workout implements IWorkout, Iterable<Exercise> {
  private ArrayList<Exercise> exercises;
  private LocalDate workoutDate;

  public Workout(LocalDate workoutDate) {
    this.exercises = new ArrayList<>();
    this.workoutDate = workoutDate;
  }

  @Override
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

  public void addExercise(Exercise exercise) {
    this.exercises.add(exercise);
  }

  @Override
  public Iterator<Exercise> iterator() {
    ArrayList<Exercise> temp = new ArrayList<>(this.exercises);
    return temp.iterator();
  }
}
