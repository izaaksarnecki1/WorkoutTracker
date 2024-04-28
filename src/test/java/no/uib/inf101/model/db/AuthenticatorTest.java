package no.uib.inf101.model.db;

import static org.junit.jupiter.api.Assertions.*;

import no.uib.inf101.model.User;
import org.junit.jupiter.api.*;

public class AuthenticatorTest {

    private static final String TEST_DB_PATH = "jdbc:sqlite:src/test/resources/db/test-db.db";

    private Authenticator authenticator;
    private DatabaseController dbController;

    @BeforeEach
    public void setup() {
        dbController = new DatabaseController(TEST_DB_PATH);
        authenticator = new Authenticator(dbController);
    }

    @AfterEach
    public void teardown() {
        dbController.dropTables();
    }

    // Tests for createNewUser method

    @Test
    public void testCreateNewUserValid() {
        User user = authenticator.createNewUser("testuser", "password");

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testCreateNewUserWithExistingUsername() {
        // First, create a user with a specific username
        User existingUser = authenticator.createNewUser("existinguser", "password");

        // Attempt to create a new user with the same username
        User newUser = authenticator.createNewUser("existinguser", "password");

        assertNull(newUser); // Ensure that the second user creation attempt fails
    }

    @Test
    public void testCreateNewUserWithInvalidPassword() {
        User user = authenticator.createNewUser("testuser", "");

        assertNull(user); // Ensure that user creation fails due to invalid password
    }

    @Test
    public void testLoginUserValidCredentials() {
        // First, create a user with specific credentials
        authenticator.createNewUser("testuser", "password");

        // Attempt to login with the same credentials
        User user = authenticator.loginUser("testuser", "password");

        assertNotNull(user); // Ensure that login is successful
    }

    @Test
    public void testLoginUserInvalidUsername() {
        User user = authenticator.loginUser("nonexistentuser", "password");

        assertNull(user); // Ensure that login fails due to nonexistent username
    }

    @Test
    public void testLoginUserInvalidPassword() {
        // First, create a user with specific credentials
        authenticator.createNewUser("testuser", "password");

        // Attempt to login with incorrect password
        User user = authenticator.loginUser("testuser", "wrongpassword");

        assertNull(user); // Ensure that login fails due to incorrect password
    }
}
