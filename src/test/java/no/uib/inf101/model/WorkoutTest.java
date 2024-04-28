package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class WorkoutTest {

    @Test
    public void testValidDate() {
        // Arrange
        String validDate = "2024-04-30";
        Workout workout = new Workout(1, validDate, "Leg Day");

        // Act
        String workoutDate = workout.getWorkoutDate();

        // Assert
        assertEquals(validDate, workoutDate);
    }

    @Test
    public void testInvalidDate() {
        // Arrange
        String invalidDate = "2024-04-30T12:00:00"; // Invalid format
        Workout workout = new Workout(1, invalidDate, "Chest Day");

        // Act
        String workoutDate = workout.getWorkoutDate();

        // Assert
        assertNotNull(workoutDate);
        assertNotEquals(invalidDate, workoutDate);
    }

    @Test
    public void testGetWorkoutDate() {
        // Arrange
        Workout workout = new Workout(1, "2024-04-30", "Leg Day");

        // Act
        String workoutDate = workout.getWorkoutDate();

        // Assert
        assertEquals("2024-04-30", workoutDate);
    }

    @Test
    public void testGetWorkoutName() {
        // Arrange
        Workout workout = new Workout(1, "2024-04-30", "Chest Day");

        // Act
        String workoutName = workout.getWorkoutName();

        // Assert
        assertEquals("Chest Day", workoutName);
    }

    @Test
    public void testSetWorkoutDate() {
        // Arrange
        Workout workout = new Workout(1, "2024-04-30", "Back Day");

        // Act
        workout.setWorkoutDate("2024-05-01");

        // Assert
        assertEquals("2024-05-01", workout.getWorkoutDate());
    }

    @Test
    public void testSetWorkoutName() {
        // Arrange
        Workout workout = new Workout(1, "2024-04-30", "Arm Day");

        // Act
        workout.setWorkoutName("Shoulder Day");

        // Assert
        assertEquals("Shoulder Day", workout.getWorkoutName());
    }

    @Test
    public void testGetWorkoutId() {
        // Arrange
        Workout workout = new Workout(1, "2024-04-30", "Leg Day");
        workout.setWorkoutId(123);

        // Act
        int workoutId = workout.getId();

        // Assert
        assertEquals(123, workoutId);
    }
}

