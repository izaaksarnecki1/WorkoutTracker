package no.uib.inf101.model.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import no.uib.inf101.model.User;

import java.util.HashMap;

public class DatabaseControllerTest {

    private static final String TEST_DB_PATH = "jdbc:sqlite:src/test/resources/db/test-db.db";

    private DatabaseController dbController;

    @BeforeEach
    public void setup() {
        dbController = new DatabaseController(TEST_DB_PATH);
    }

    @AfterEach
    public void teardown() {
        dbController.dropTables();
    }

    @Test
    public void testDatabaseSetup() {
        // Verify that tables are created during setup
        assertTrue(dbController.tableExists("users"));
        assertTrue(dbController.tableExists("workouts"));
        assertTrue(dbController.tableExists("exercises"));
    }

    @Test
    public void testInsertRow() {
        // Create a dummy user entity for testing
        User user = new User("testuser", "password");
        user.setFirstName("Kari");
        user.setLastName("Nordmann");
        user.setWeight(70);
        user.setHeight(180);

        // Insert the user into the database
        dbController.insertRow(user);

        // Retrieve the inserted user from the database
        User retrievedUser = dbController.getUserByUsername("testuser");

        // Verify that the retrieved user matches the inserted user
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        assertEquals("Kari", retrievedUser.getFirstName());
        assertEquals("Nordmann", retrievedUser.getLastName());
        assertEquals(70, retrievedUser.getWeight());
        assertEquals(180, retrievedUser.getHeight());
    }

    // Add more tests for other functionalities

}
