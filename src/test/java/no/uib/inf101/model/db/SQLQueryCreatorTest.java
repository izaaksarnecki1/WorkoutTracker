package no.uib.inf101.model.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import no.uib.inf101.model.DbUploadable;
import no.uib.inf101.model.User;

public class SQLQueryCreatorTest {

    @Test
    public void testUpdateRowSQLString() {
        // Create a dummy entity for testing
        DbUploadable dummyEntity = new User("username", "password");

        // Expected SQL query string
        String expectedQuery = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, weight = ?, height = ? WHERE id = ?";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.updateRowSQLString(dummyEntity);

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetTableSQLString() {
        // Test with User table name
        String userTableQuery = SQLQueryCreator.getTableSQLString(User.TABLE_NAME);
        String expectedString = "CREATE TABLE IF NOT EXISTS users (\n" +
                "   id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "   username text NOT NULL UNIQUE,\n" +
                "   password text NOT NULL,\n" +
                "   first_name text,\n" +
                "   last_name text,\n" +
                "   weight int,\n" +
                "   height int\n" +
                ");";
        
        // Normalize both strings to ignore whitespace differences
        userTableQuery = userTableQuery.replaceAll("\\s+", " ").trim();
        expectedString = expectedString.replaceAll("\\s+", " ").trim();
    
        // Compare the normalized strings
        assertEquals(expectedString, userTableQuery);
    }
    @Test
    public void testGetRowSQLString() {
        // Create a dummy entity for testing
        DbUploadable dummyEntity = new User("username", "password");
        dummyEntity.setId(1);

        // Expected SQL query string
        String expectedQuery = "SELECT * FROM users WHERE id = 1";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.getRowSQLString(dummyEntity);

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetLastIdSQLString() {
        // Create a dummy entity for testing
        DbUploadable dummyEntity = new User("username", "password");

        // Expected SQL query string
        String expectedQuery = "SELECT seq FROM sqlite_sequence WHERE name=\"users\"";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.getLastIdSQLString(dummyEntity);

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetUserWorkouts() {
        // Create a dummy entity for testing
        DbUploadable dummyEntity = new User("username", "password");
        dummyEntity.setId(1);

        // Expected SQL query string
        String expectedQuery = "SELECT * FROM workouts WHERE user_id = 1 ORDER BY date DESC";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.getUserWorkouts(dummyEntity);

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetWorkoutExercises() {
        // Expected SQL query string
        String expectedQuery = "SELECT * FROM exercise WHERE workout_id = 1";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.getWorkoutExercises(1);

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testGetUserByUsernameSQL() {
        // Expected SQL query string
        String expectedQuery = "SELECT * FROM users WHERE username = \"username\"";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.getUserByUsernameSQL("username");

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testValidatePassSQL() {
        // Expected SQL query string
        String expectedQuery = "SELECT password FROM users WHERE username = \"username\"";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.validatePassSQL("username");

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void testFetchUserIdSQL() {
        // Expected SQL query string
        String expectedQuery = "SELECT id FROM users WHERE username = \"username\"";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.fetchUserIdSQL("username");

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    public void enablePragmaTest() {
        // Expected SQL query string
        String expectedQuery = "PRAGMA foreign_keys = ON;";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.enablePragma();

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }    

    @Test
    public void dropTableTest() {
        // Expected SQL query string
        String expectedQuery = "DROP TABLE IF EXISTS users;";

        // Generate SQL query string using SQLQueryCreator
        String actualQuery = SQLQueryCreator.dropTableSQL("users");

        // Assert that the generated query matches the expected query
        assertEquals(expectedQuery, actualQuery);
    }
}
