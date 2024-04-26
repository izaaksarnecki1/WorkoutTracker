package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
  private List<Exercise> exercises;

  // JList to display workouts in window
  public Workout(int userId, String workoutDate, String workoutName) {
    this.exercises = new ArrayList<>();
    this.workoutDate = validateDate(workoutDate);
    this.userId = userId;
    this.workoutName = workoutName;
  }

  public String getWorkoutDate() {
    return this.workoutDate;
  }

  public String getWorkoutName() {
    return this.workoutName;
  }

  public void setWorkoutDate(String workoutDate) {
    this.workoutDate = workoutDate;
  }

  public void setWorkoutName(String workoutName) {
    this.workoutName = workoutName;
  }

  protected void setWorkoutId(int workoutId) {
    this.workoutId = workoutId;
  }

  public void addExercise(Exercise exercise) {
    if (validExercise(exercise)) {
      this.exercises.add(exercise);
    } else {
      System.err.println("Failed to add exercise: " + exercise);
    }
  }

  boolean validExercise(Exercise exercise) {
    return !exercise.getExerciseName().isEmpty()
        && exercise.getReps() != 0
        && exercise.getSets() != 0;
  }

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
}
