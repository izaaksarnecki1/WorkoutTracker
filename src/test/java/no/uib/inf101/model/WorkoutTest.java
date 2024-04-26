// package no.uib.inf101.model;

// import org.junit.jupiter.api.Test;

// import java.time.LocalDate;
// import java.util.Iterator;

// import static org.junit.jupiter.api.Assertions.*;

// public class WorkoutTest {
//   @Test
//   void addExercise() {
//     Workout workout = new Workout(1, LocalDate.now());
//     Exercise exercise = new Exercise("Push-up", 10, 3);
//     workout.addExercise(exercise);
//     assertTrue(workout.iterator().hasNext());
//   }

//   @Test
//   void validExercise() {
//     Workout workout = new Workout(1, LocalDate.now());
//     Exercise validExercise = new Exercise("Squat", 8, 4);
//     Exercise invalidExercise = new Exercise("", 0, 0);
//     assertTrue(workout.validExercise(validExercise));
//     assertFalse(workout.validExercise(invalidExercise));
//   }

//   @Test
//   void getWorkoutDate() {
//     LocalDate date = LocalDate.now();
//     Workout workout = new Workout(1, date);
//     assertEquals(date, workout.getWorkoutDate());
//   }

//   @Test
//   void setWorkoutDate() {
//     LocalDate date = LocalDate.now();
//     Workout workout = new Workout(1, LocalDate.now());
//     workout.setWorkoutDate(date);
//     assertEquals(date, workout.getWorkoutDate());
//   }

//   @Test
//   void iterator() {
//     Workout workout = new Workout(1, LocalDate.now());
//     Exercise exercise = new Exercise("Jumping Jacks", 20, 3);
//     workout.addExercise(exercise);
//     Iterator<Exercise> iterator = workout.iterator();
//     assertNotNull(iterator);
//     assertTrue(iterator.hasNext());
//     assertEquals(exercise, iterator.next());
//   }

//   @Test
//   void getUploadableData() {
//     LocalDate date = LocalDate.now();
//     Workout workout = new Workout(1, date);
//     Exercise exercise = new Exercise("Burpees", 15, 3);
//     workout.addExercise(exercise);
//     assertEquals(workout.getWorkoutDate(), workout.getUploadableData().get("date"));
//     assertEquals(1, workout.getUploadableData().get("user_id"));
//   }

//   @Test
//   void getTableName() {
//     assertEquals("workouts", Workout.tableName);
//   }

//   @Test
//   void getAttributeNames() {
//     Workout workout = new Workout(1, LocalDate.now());
//     assertEquals(2, workout.getAttributeNames().size());
//     assertTrue(workout.getAttributeNames().contains("date"));
//     assertTrue(workout.getAttributeNames().contains("user_id"));
//   }

//   @Test
//   void getParent() {
//     assertEquals(User.tableName, new Workout(1, LocalDate.now()).getParent());
//   }
// }
