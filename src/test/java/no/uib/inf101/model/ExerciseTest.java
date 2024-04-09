//package no.uib.inf101.model;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import java.util.Calendar;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ExerciseTest {
//
//  private Exercise exercise;
//
//  @BeforeEach
//  public void setUp() {
//    Calendar date = Calendar.getInstance();
//    exercise = new Exercise("Push-ups", 3, 10, 50, date);
//  }
//
//  @Test
//  public void testGetExerciseName() {
//    assertEquals("Push-ups", exercise.getExerciseName());
//  }
//
//  @Test
//  public void testGetSets() {
//    assertEquals(3, exercise.getSets());
//  }
//
//  @Test
//  public void testGetReps() {
//    assertEquals(10, exercise.getReps());
//  }
//
//  @Test
//  public void testGetWeight() {
//    assertEquals(50, exercise.getWeight());
//  }
//
//  @Test
//  public void testGetWorkoutDate() {
//    assertNotNull(exercise.getWorkoutDate());
//  }
//
//  @Test
//  public void testSetExerciseName() {
//    exercise.setExerciseName("Pull-ups");
//    assertEquals("Pull-ups", exercise.getExerciseName());
//  }
//
//  @Test
//  public void testSetSets() {
//    exercise.setSets(4);
//    assertEquals(4, exercise.getSets());
//  }
//
//  @Test
//  public void testSetReps() {
//    exercise.setReps(12);
//    assertEquals(12, exercise.getReps());
//  }
//
//  @Test
//  public void testSetWeight() {
//    exercise.setWeight(60);
//    assertEquals(60, exercise.getWeight());
//  }
//
//  @Test
//  public void testSetWorkoutDate() {
//    Calendar newDate = Calendar.getInstance();
//    exercise.setWorkoutDate(newDate);
//    assertEquals(newDate, exercise.getWorkoutDate());
//  }
//
//  @Test
//  public void testToString() {
//    String expected = "Exercise_Name: Push-ups | Sets: 3 | Reps: 10 | Weight: 50 | Date: " + exercise.getWorkoutDate().toString();
//    assertEquals(expected, exercise.toString());
//  }
//
//  @Test
//  public void testEquals() {
//    exercise.setWorkoutDate(Calendar.);
//    Calendar date = Calendar.getInstance();
//    Exercise sameExercise = new Exercise("Push-ups", 3, 10, 50, date);
//    assertEquals(exercise, sameExercise);
//  }
//
//  @Test
//  public void testNotEquals() {
//    Calendar date = Calendar.getInstance();
//    Exercise differentExercise = new Exercise("Squats", 3, 10, 50, date);
//    assertNotEquals(exercise, differentExercise);
//  }
//
//  @Test
//  public void testHashCode() {
//    Calendar date = Calendar.getInstance();
//    Exercise sameExercise = new Exercise("Push-ups", 3, 10, 50, date);
//    assertEquals(exercise.hashCode(), sameExercise.hashCode());
//  }
//}
