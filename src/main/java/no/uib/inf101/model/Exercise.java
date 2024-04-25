package no.uib.inf101.model;

import java.util.*;

public class Exercise implements DbUploadable {
  public static final String TABLE_NAME = "exercise";
  public static final String WORKOUT_ID = "workout_id";
  public static final String EXERCISE_NAME = "ex_name";
  public static final String SETS = "sets";
  public static final String REPS = "reps";
  public static final String WEIGHT = "weight";

  private final ArrayList<String> attributeNames = new ArrayList<>(
      Arrays.asList(WORKOUT_ID, EXERCISE_NAME, SETS, REPS, WEIGHT));
  
  private final int workoutId;
  private int exerciseId = 0;
  private String exerciseName;
  private int sets;
  private int reps;
  private int weight;

  public Exercise(int workoutId, String exerciseName, int sets, int reps, int weight) {
    this.workoutId = workoutId;
    this.exerciseName = exerciseName;
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
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

  public void setExerciseId(int exerciseId) {
    this.exerciseId = exerciseId;
  }

  @Override
  public String toString() {
    return String.format(
        "Exercise_Name: %s | Sets: %d | Reps: %d | Weight: %d",
        exerciseName, sets, reps, weight);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
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
    uploadableData.put(WORKOUT_ID, this.workoutId);
    uploadableData.put(EXERCISE_NAME, this.exerciseName);
    uploadableData.put(SETS, this.sets);
    uploadableData.put(REPS, this.reps);
    uploadableData.put(WEIGHT, this.weight);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return this.attributeNames;
  }

  @Override
  public String getParent() {
    return Workout.TABLE_NAME;
  }

  @Override
  public int getId() {
    return this.workoutId;
  }
}
