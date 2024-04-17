package no.uib.inf101.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Workout implements Iterable<Exercise>, DbUploadable {
  public static final String tableName = "workouts";
  private List<Exercise> exercises;
  private LocalDate workoutDate;
  private final int user_id;

  public Workout(int user_id, LocalDate workoutDate) {
    this.exercises = new ArrayList<>();
    this.workoutDate = workoutDate;
    this.user_id = user_id;
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

  boolean validExercise(Exercise exercise) {
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
    uploadableData.put("user_id", this.user_id);
    return uploadableData;
  }

  @Override
  public String getTableName() {
    return tableName;
  }

  @Override
  public ArrayList<String> getAttributeNames() {
    return new ArrayList<>(Arrays.asList("date", "user_id"));
  }

  @Override
  public String getParent() {
    return User.tableName;
  }
}
