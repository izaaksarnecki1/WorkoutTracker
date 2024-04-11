package no.uib.inf101.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise implements DbUploadable{
  private String exerciseName;
  private int sets;
  private int reps;
  private int weight;

  public Exercise(String exerciseName, int sets, int reps, int weight) {
    this.exerciseName = exerciseName;
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
  }

  public Exercise(String exerciseName, int sets, int reps) {
    this(exerciseName, sets, reps, 0);
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

  @Override
  public String toString() {
    return String.format(
            "Exercise_Name: %s | Sets: %d | Reps: %d | Weight: %d",
            exerciseName, sets, reps, weight
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
            && Objects.equals(exerciseName, exercise.exerciseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exerciseName, sets, reps, weight);
  }

  @Override
  public List<Object> getUploadableData() {
    ArrayList<Object> uploadableData = new ArrayList<>();
    uploadableData.add(this.exerciseName);
    uploadableData.add(this.sets);
    uploadableData.add(this.reps);
    uploadableData.add(this.weight);
    return uploadableData;
  }
}
