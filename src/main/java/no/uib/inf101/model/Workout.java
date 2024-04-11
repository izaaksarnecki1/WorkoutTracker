package no.uib.inf101.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Workout implements Iterable<Exercise>, DbUploadable {
  private List<Exercise> exercises;
  private LocalDate workoutDate;

  public Workout(LocalDate workoutDate) {
    this.exercises = new ArrayList<>();
    this.workoutDate = workoutDate;
  }

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
    if (validExercise(exercise)) {
      this.exercises.add(exercise);
    } else {
      System.err.println("Failed to add exercise: " + exercise);
    }
  }

  private boolean validExercise(Exercise exercise) {
    return !exercise.getExerciseName().isEmpty()
            && exercise.getReps() != 0
            && exercise.getSets() != 0;
  }

  @Override
  public Iterator<Exercise> iterator() {
    List<Exercise> temp = new ArrayList<>(this.exercises);
    return temp.iterator();
  }

  @Override
  public HashMap<String, Object> getUploadableData() {
    HashMap<String, Object> uploadableData = new HashMap<>();
    uploadableData.put("date", this.workoutDate);
    return null;
  }

  @Override
  public String getTableName() {
    return "workouts";
  }
}
