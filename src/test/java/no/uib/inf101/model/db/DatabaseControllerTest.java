// package no.uib.inf101.model.db;

// import no.uib.inf101.model.DbUploadable;
// import no.uib.inf101.model.User;
// import org.junit.jupiter.api.*;

// import static org.junit.jupiter.api.Assertions.*;

// class DatabaseControllerTest {

//   @BeforeAll
//   static void setUp() {
//     // Set up tables before running tests
//     DatabaseController controller = new DatabaseController();
//     controller.setupDb(true);
//   }

// //  @Test
// //  void addRow_ValidEntity_Success() {
// //    // Given
// //    DbUploadableMock entity = new DbUploadableMock();
// //
// //    // When
// //    DatabaseController.addRow(entity);
// //
// //    // Then: Verify that the row has been added successfully
// //    // You can perform additional verification here, such as checking the database directly
// //    // to ensure the row has been inserted properly.
// //  }

//   @Test
//   void validatePass_ValidCredentials_Success() {
//     String username = "testuser";
//     String password = "testpassword";
//     DbUploadable userEntity = new User(username, password);
//     DatabaseController.insertRow(userEntity);

//     boolean result = DatabaseController.validatePass(username, password);

//     assertTrue(result);
//   }

//   @Test
//   void validatePass_InvalidCredentials_Failure() {
//     // Given
//     String username = "testuser";
//     String password = "testpassword";
//     String wrongPassword = "wrongpassword";
//     DbUploadable userEntity = new User(username, password);
//     DatabaseController.insertRow(userEntity);

//     // When
//     boolean result = DatabaseController.validatePass(username, wrongPassword);

//     // Then
//     assertFalse(result);
//   }

//   @Test
//   void fetchUserId_ValidUsername_Success() {
//     // Given
//     String username = "testuser";
//     String password = "testpassword";
//     String expectedUserId = "1";

//     DbUploadable userEntity = new User(username, password);
//     DatabaseController.insertRow(userEntity);

//     String userId = DatabaseController.fetchUserId(username);

//     assertEquals(expectedUserId, userId);
//   }

//   @Test
//   void fetchUserId_InvalidUsername_Failure() {
//     // Given
//     String username = "nonexistentuser";

//     // When
//     String userId = DatabaseController.fetchUserId(username);

//     // Then
//     assertEquals(userId, "0");
//   }
// }