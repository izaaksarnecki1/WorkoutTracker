package no.uib.inf101.model.db;

import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.User;
import no.uib.inf101.model.Workout;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SQLQueryCreatorTest {

  @Test
  void createAddRowString() {
    DbUploadable uploadable = new User("test", "password");
    SQLQueryCreator creator = new SQLQueryCreator(uploadable);
    String expectedSQL = "INSERT INTO users(username, password) VALUES(?, ?)";
    assertEquals(expectedSQL, creator.createAddRowString());
  }

  @Test
  void getTableSQLString() {
    String userTableSQL = SQLQueryCreator.getTableSQLString("users");
    String expectedUserTableSQL = "CREATE TABLE IF NOT EXISTS users (\n" +
            "	id integer PRIMARY KEY,\n" +
            "	username text NOT NULL UNIQUE,\n" +
            "	password text NOT NULL\n" +
            ");";
    assertEquals(expectedUserTableSQL, userTableSQL);

    String workoutTableSQL = SQLQueryCreator.getTableSQLString("workouts");
    String expectedWorkoutTableSQL = "CREATE TABLE IF NOT EXISTS workouts (\n" +
            "	id integer PRIMARY KEY,\n" +
            " user_id integer, \n" +
            "	date text,\n" +
            " FOREIGN KEY (user_id) REFERENCES users (id)" +
            ");";
    assertEquals(expectedWorkoutTableSQL, workoutTableSQL);

    String exerciseTableSQL = SQLQueryCreator.getTableSQLString("exercise");
    String expectedExerciseTableSQL = "CREATE TABLE IF NOT EXISTS exercise (\n" +
            "	id integer PRIMARY KEY,\n" +
            " workout_id integer, \n" +
            "	ex_name text NOT NULL,\n" +
            "	sets integer NOT NULL,\n" +
            "	reps integer NOT NULL,\n" +
            "	weight text,\n" +
            " FOREIGN KEY (workout_id) REFERENCES workouts (id)" +
            ");";
    assertEquals(expectedExerciseTableSQL, exerciseTableSQL);
  }
}