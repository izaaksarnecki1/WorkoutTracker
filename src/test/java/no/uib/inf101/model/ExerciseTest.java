// package no.uib.inf101.model;

// import org.junit.jupiter.api.Test;

// import java.util.Arrays;
// import java.util.HashMap;

// import static org.junit.jupiter.api.Assertions.*;

// public class ExerciseTest {

//   @Test
//   void getSetExerciseName() {
//     Exercise exercise = new Exercise("Push-up", 3, 10);
//     exercise.setExerciseName("Pull-up");
//     assertEquals("Pull-up", exercise.getExerciseName());
//   }

//   @Test
//   void getSetSets() {
//     Exercise exercise = new Exercise("Squat", 4, 8);
//     exercise.setSets(5);
//     assertEquals(5, exercise.getSets());
//   }

//   @Test
//   void getSetReps() {
//     Exercise exercise = new Exercise("Deadlift", 3, 12);
//     exercise.setReps(15);
//     assertEquals(15, exercise.getReps());
//   }

//   @Test
//   void getSetWeight() {
//     Exercise exercise = new Exercise("Bench Press", 4, 10, 50);
//     exercise.setWeight(60);
//     assertEquals(60, exercise.getWeight());
//   }

//   @Test
//   void getUploadableData() {
//     Exercise exercise = new Exercise("Sit-up", 3, 20);
//     exercise.setWeight(0);
//     HashMap<String, Object> expectedData = new HashMap<>();
//     expectedData.put("ex_name", "Sit-up");
//     expectedData.put("sets", 3);
//     expectedData.put("reps", 20);
//     expectedData.put("weight", 0);
//     assertEquals(expectedData, exercise.getUploadableData());
//   }

//   @Test
//   void getTableName() {
//     assertEquals("exercises", Exercise.TABLE_NAME);
//   }
// }