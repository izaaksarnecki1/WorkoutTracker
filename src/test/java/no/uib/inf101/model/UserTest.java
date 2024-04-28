package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testConstructor() {
        User user = new User("kari_nordmann", "password123");
        assertEquals("kari_nordmann", user.getUsername());
        assertEquals("", user.getFirstName());
        assertEquals("", user.getLastName());
        assertEquals(0, user.getWeight());
        assertEquals(0, user.getHeight());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User("kari_nordmann", "password123");
        user.setFirstName("Kari");
        user.setLastName("Nordmann");
        user.setWeight(70);
        user.setHeight(180);

        assertEquals("Kari", user.getFirstName());
        assertEquals("Nordmann", user.getLastName());
        assertEquals(70, user.getWeight());
        assertEquals(180, user.getHeight());
    }

    @Test
    public void testUploadableData() {
        User user = new User("kari_nordmann", "password123");
        user.setFirstName("Kari");
        user.setLastName("Nordmann");
        user.setWeight(70);
        user.setHeight(180);

        HashMap<String, Object> expectedUploadableData = new HashMap<>();
        expectedUploadableData.put(User.USERNAME, "kari_nordmann");
        expectedUploadableData.put(User.PASSWORD, "password123");
        expectedUploadableData.put(User.FIRST_NAME, "Kari");
        expectedUploadableData.put(User.LAST_NAME, "Nordmann");
        expectedUploadableData.put(User.WEIGHT, 70);
        expectedUploadableData.put(User.HEIGHT, 180);

        assertEquals(expectedUploadableData, user.getUploadableData());
    }

    @Test
    public void testTableName() {
        User user = new User("kari_nordmann", "password123");
        assertEquals(User.TABLE_NAME, user.getTableName());
    }

    @Test
    public void testAttributeNames() {
        User user = new User("kari_nordmann", "password123");
        ArrayList<String> expectedAttributeNames = new ArrayList<>();
        expectedAttributeNames.add(User.USERNAME);
        expectedAttributeNames.add(User.PASSWORD);
        expectedAttributeNames.add(User.FIRST_NAME);
        expectedAttributeNames.add(User.LAST_NAME);
        expectedAttributeNames.add(User.WEIGHT);
        expectedAttributeNames.add(User.HEIGHT);

        assertEquals(expectedAttributeNames, user.getAttributeNames());
    }

    @Test
    void testExtremeValues() {
        User user = new User("kari.nordmann", "password");

        // Test setting extreme values for weight and height
        user.setWeight(Integer.MAX_VALUE);
        user.setHeight(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, user.getWeight());
        assertEquals(Integer.MAX_VALUE, user.getHeight());

        // Test setting negative values for weight and height
        user.setWeight(-100);
        user.setHeight(-200);

        assertEquals(-100, user.getWeight());
        assertEquals(-200, user.getHeight());
    }
}
