package no.uib.inf101.model;

import no.uib.inf101.db.DatabaseController;

import java.util.*;

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
  public HashMap<String, Object> getUploadableData() {
    HashMap<String, Object> uploadableData = new HashMap<>();
    uploadableData.put("ex_name", this.exerciseName );
    uploadableData.put("sets", this.sets);
    uploadableData.put("reps", this.reps);
    uploadableData.put("weight", this.weight);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return "exercises";
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return new ArrayList<>(Arrays.asList("ex_name", "sets", "reps", "weight"));
  }
}
