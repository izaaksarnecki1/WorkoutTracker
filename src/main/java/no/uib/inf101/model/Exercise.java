package no.uib.inf101.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

public class Exercise {

  private String exerciseName;
  private int sets;
  private int reps;
  private int weight;
  private LocalDate workoutDate;

  public Exercise(String exerciseName, int sets, int reps, int weight, LocalDate date) {
    this.exerciseName = exerciseName;
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
    this.workoutDate = date;
  }

  public Exercise(String exerciseName, int sets, int reps, LocalDate date) {
    this(exerciseName, sets, reps, 0, date);
  }

  public Exercise(String exerciseName, int sets, int reps, int weight) {
    this(exerciseName, sets, reps, weight, null);
  }

  public String getExerciseName() {
    return this.exerciseName;
  }

  public int getReps() {
    return this.reps;
  }

  public int getSets() {
    return this.sets;
  }

  public int getWeight() {
    return this.weight;
  }
  public LocalDate getWorkoutDate() {
    return workoutDate;
  }

  public void setExerciseName(String exerciseName) {
    if (!exerciseName.isBlank() && !exerciseName.isEmpty()) {
      this.exerciseName = exerciseName;
    } else {
      System.err.println("Invalid Exercise Name");
    }
  }

  public void setSets(int sets) {
    this.sets = sets;
  }

  public void setReps(int reps) {
    this.reps = reps;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public void setWorkoutDate(LocalDate workoutDate) {
    this.workoutDate = workoutDate;
  }

  @Override
  public String toString() {
    return String.format(
            "Exercise_Name: %s | Sets: %d | Reps: %d | Weight: %d | Date: %s",
            exerciseName, sets, reps, weight, workoutDate.toString()
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Exercise exercise = (Exercise) o;
    return sets == exercise.sets
            && reps == exercise.reps
            && weight == exercise.weight
            && Objects.equals(exerciseName, exercise.exerciseName)
            && Objects.equals(workoutDate, exercise.workoutDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exerciseName, sets, reps, weight, workoutDate);
  }
}
