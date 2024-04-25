package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Workout implements Iterable<Exercise>, DbUploadable {
  public static final String TABLE_NAME = "workouts";
  public static final String WORKOUTDATE = "date";
  public static final String USERID = "user_id";
  public static final String WORKOUTNAME = "workout_name";

  private final ArrayList<String> attirbuteNames = new ArrayList<>(
      Arrays.asList(USERID, WORKOUTNAME, WORKOUTDATE));

  private final int userId;
  private final int workoutId = 0;
  private String workoutName;
  private LocalDate workoutDate;
  private List<Exercise> exercises;

  // JList to display workouts in window
  public Workout(int userId, LocalDate workoutDate, String workoutName) {
    this.exercises = new ArrayList<>();
    this.workoutDate = workoutDate;
    this.userId = userId;
    this.workoutName = workoutName;
  }

  public LocalDate getWorkoutDate() {
    return this.workoutDate;
  }

  public int getWorkoutDateAsInt() {
    return Integer.parseInt(
        this.workoutDate.format(DateTimeFormatter.BASIC_ISO_DATE));
  }

  public void setWorkoutDate(LocalDate workoutDate) {
    this.workoutDate = workoutDate;
  }

  public void setWorkoutName(String workoutName) {
    this.workoutName = workoutName;
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

  // IDK IF THIS IS NEEDED
  @Override
  public Iterator<Exercise> iterator() {
    List<Exercise> temp = new ArrayList<>(this.exercises);
    return temp.iterator();
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
  public String getParent() {
    return User.TABLE_NAME;
  }

  @Override
  public int getId() {
    return 0;
  }
}
