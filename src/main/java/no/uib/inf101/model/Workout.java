package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Represents a workout.
 * 
 * This class encapsulates information about a workout, including the user ID, workout date, workout name,
 * and a list of exercises. It provides methods to get and set the workout date and name, add exercises to
 * the workout, and validate exercise data. It also implements the DbUploadable interface for uploading
 * workout data to a database.
 */
public class Workout implements DbUploadable {
  public static final String TABLE_NAME = "workouts";
  public static final String WORKOUTDATE = "date";
  public static final String USERID = "user_id";
  public static final String WORKOUTNAME = "workout_name";

  private final ArrayList<String> attirbuteNames = new ArrayList<>(
      Arrays.asList(USERID, WORKOUTNAME, WORKOUTDATE));

  private final int userId;
  private int workoutId = 0;
  private String workoutName;
  private String workoutDate;

  /**
   * Constructs a new Workout object.
   *
   * @param userId      the ID of the user associated with the workout
   * @param workoutDate the date of the workout
   * @param workoutName the name of the workout
   */
  public Workout(int userId, String workoutDate, String workoutName) {
    this.workoutDate = validateDate(workoutDate);
    this.userId = userId;
    this.workoutName = workoutName;
  }

  /**
   * Gets the date of the workout.
   *
   * @return the date of the workout
   */
  public String getWorkoutDate() {
    return this.workoutDate;
  }

  /**
   * Gets the name of the workout.
   *
   * @return the name of the workout
   */
  public String getWorkoutName() {
    return this.workoutName;
  }

  /**
   * Sets the date of the workout.
   *
   * @param workoutDate the date of the workout
   */
  public void setWorkoutDate(String workoutDate) {
    this.workoutDate = workoutDate;
  }

  /**
   * Sets the name of the workout.
   *
   * @param workoutName the name of the workout
   */
  public void setWorkoutName(String workoutName) {
    this.workoutName = workoutName;
  }

  /**
   * Sets the ID of the workout.
   *
   * @param workoutId the ID of the workout
   */
  protected void setWorkoutId(int workoutId) {
    this.workoutId = workoutId;
  }

  /**
   * Checks if an exercise is valid.
   *
   * @param exercise the exercise to check
   * @return true if the exercise is valid, false otherwise
   */
  boolean validExercise(Exercise exercise) {
    return !exercise.getExerciseName().isEmpty()
        && exercise.getReps() != 0
        && exercise.getSets() != 0;
  }

  /**
   * Validates a date string.
   *
   * @param date the date string to validate
   * @return the validated date string or the current date if the input is invalid
   */
  private String validateDate(String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate.parse(date, formatter);
      return date;
    } catch (Exception e) {
      System.err.println("Invalid date format: " + date);
      return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
  }

  @Override
  public HashMap<String, Object> getUploadableData() {
    HashMap<String, Object> uploadableData = new HashMap<>();
    uploadableData.put(WORKOUTDATE, this.workoutDate);
    uploadableData.put(WORKOUTNAME, this.workoutName);
    uploadableData.put(USERID, this.userId);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return this.attirbuteNames;
  }

  @Override
  public int getId() {
    return this.workoutId;
  }

  @Override
  public void setId(int id) {
    this.workoutId = id;
  }
}
