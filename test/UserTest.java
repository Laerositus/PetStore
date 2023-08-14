import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    /**
     * Tests user constructor
     */
    @Test
    void testConstructor(){
        // Arrange
        String firstName = "test";
        String lastName = "tester";
        String email = "t.tester@email.com";
        int budget = 0;

        // Act
        User testUser = new User (firstName, lastName, email, budget);

        // Assert
        assertEquals(testUser.firstName, "test");
        assertNotNull(testUser);
        assertNotNull(testUser.lastName);
        assertNotEquals(testUser.emailAddress, "notThis@email.com");
    }

}
