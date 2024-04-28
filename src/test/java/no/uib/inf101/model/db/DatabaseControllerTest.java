package no.uib.inf101.model.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import no.uib.inf101.model.User;

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
        assertTrue(dbController.tableExists("exercise"));
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

    @Test
    public void testValidatePass() {
        // Insert a dummy user into the database
        User user = new User("testuser", "password");
        dbController.insertRow(user);

        // Validate the password for the inserted user
        boolean isValid = dbController.validatePass("testuser", "password");

        // Verify that the password validation is successful
        assertTrue(isValid);
    }

    @Test
    public void testFetchUserId() {
        // Insert a dummy user into the database
        User user = new User("testuser", "password");
        dbController.insertRow(user);

        // Fetch the user ID for the inserted user
        String userId = dbController.fetchUserId("testuser");

        // Since this is the first entry in the db, user id should be 1.
        assertEquals("1", userId);
    }

    @Test
    public void testTableExists() {
        // Verify that the "users" table exists
        assertTrue(dbController.tableExists("users"));
        // Verify that a non-existent table doesn't exist
        assertFalse(dbController.tableExists("non_existent_table"));
    }

    @Test
    public void testInitiateTables() {
        // Drop all tables
        dbController.dropTables();
        // Verify that tables are not present
        assertFalse(dbController.tableExists("users"));
        assertFalse(dbController.tableExists("workouts"));
        assertFalse(dbController.tableExists("exercise"));
        // Initiate tables
        dbController.setupDb(false);
        // Verify that tables are created
        assertTrue(dbController.tableExists("users"));
        assertTrue(dbController.tableExists("workouts"));
        assertTrue(dbController.tableExists("exercise"));
    }

    @Test
    public void testValidatePassWithSQLInjection() {
        // Attempt SQL injection by passing a malicious username
        boolean isValid = dbController.validatePass("' OR 1=1 --", "password");

        // Verify that SQL injection attempt is prevented and validation fails
        assertFalse(isValid);
    }
}
