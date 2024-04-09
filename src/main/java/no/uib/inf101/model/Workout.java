package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Workout implements IWorkout, Iterable<WorkoutExercise> {
  private ArrayList<WorkoutExercise> exercises;
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
    int id = this.exercises.size();
    this.exercises.add(new WorkoutExercise(id, exercise));
  }

  @Override
  public Iterator<WorkoutExercise> iterator() {
    ArrayList<WorkoutExercise> temp = new ArrayList<>(this.exercises);
    return temp.iterator();
  }
}
