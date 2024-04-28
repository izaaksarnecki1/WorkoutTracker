package no.uib.inf101.model;

import java.util.*;

/**
 * Represents an exercise in a workout.
 * Implements the DbUploadable interface for uploading data to a database.
 */
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
  private String exerciseName;
  private int sets;
  private int reps;
  private int weight;

  /**
   * Constructs an Exercise object with the specified workout ID, exercise name, sets, reps, and weight.
   *
   * @param workoutId the ID of the workout this exercise belongs to
   * @param exerciseName the name of the exercise
   * @param sets the number of sets for the exercise
   * @param reps the number of reps for each set
   * @param weight the weight used for the exercise
   */
  public Exercise(int workoutId, String exerciseName, int sets, int reps, int weight) {
    this.workoutId = workoutId;
    this.exerciseName = exerciseName;
    this.sets = sets;
    this.reps = reps;
    this.weight = weight;
  }

  /**
   * Returns the name of the exercise.
   *
   * @return the exercise name
   */
  public String getExerciseName() {
    return this.exerciseName;
  }

  /**
   * Returns the number of reps for each set.
   *
   * @return the number of reps
   */
  public int getReps() {
    return this.reps;
  }

  /**
   * Returns the number of sets for the exercise.
   *
   * @return the number of sets
   */
  public int getSets() {
    return this.sets;
  }

  /**
   * Returns the weight used for the exercise.
   *
   * @return the weight
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Sets the name of the exercise.
   * If the provided exercise name is blank or empty, an error message is printed.
   *
   * @param exerciseName the new exercise name
   */
  public void setExerciseName(String exerciseName) {
    if (!exerciseName.isBlank() && !exerciseName.isEmpty()) {
      this.exerciseName = exerciseName;
    } else {
      System.err.println("Invalid Exercise Name");
    }
  }

  /**
   * Sets the number of sets for the exercise.
   *
   * @param sets the new number of sets
   */
  public void setSets(int sets) {
    this.sets = sets;
  }

  /**
   * Sets the number of reps for each set.
   *
   * @param reps the new number of reps
   */
  public void setReps(int reps) {
    this.reps = reps;
  }

  /**
   * Sets the weight used for the exercise.
   *
   * @param weight the new weight
   */
  public void setWeight(int weight) {
    this.weight = weight;
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
  public int getId() {
    return 0;
  }
}
