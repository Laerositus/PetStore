import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {
    String firstName = "test";
    String lastName = "tester";
    String email = "t.tester@email.com";
    int budget = 0;

    User testUser = new User (firstName, lastName, email, budget);

    @Test
    void testCatContructorWithoutOwner(){
        // Arrange
        String name = "test";
        AnimalType type = AnimalType.Cat;
        String description = "test description";
        Date dob = new Date();

        // Act
        Pet testPet = new Pet(null, name, type, description, dob, null);

        // Assert
        assertNotNull(testPet);
        assertNull(testPet.owner);
        assertEquals(testPet.type, AnimalType.Cat);
    }

    @Test
    void testDogContructorWithOwner(){
        // Arrange
        User owner = testUser;
        String name = "test";
        AnimalType type = AnimalType.Dog;
        String description = "test description";
        Date dob = new Date();
        Integer rating = 5;

        // Act
        Pet testPet = new Pet(owner, name, type, description, dob, rating);

        // Assert
        assertNotNull(testPet);
        assertEquals(testPet.owner, testUser);
        assertEquals(testPet.rating, 5);
    }
    @Test
    void testSetPriceCat() {
        String name = "test";
        AnimalType type = AnimalType.Cat;
        String description = "test description";
        Date dob = new Date(120, 1, 1); // 2020 = 3 yr old; 2020-1900 = 120

        // Act
        Pet testPet = new Pet(null, name, type, description, dob, null);

        // Assert
        assertEquals(testPet.price, 3); // Cat's price should be 1 * age so 3
    }

    @Test
    void testSetPriceDog(){
        String name = "test";
        AnimalType type = AnimalType.Dog;
        String description = "test description";
        Date dob = new Date(120, 1, 1); // 2020 = 3 yr old 2020-1900 = 120
        Integer rating = 5;

        // Act
        Pet testPet = new Pet(testUser, name, type, description, dob, rating);

        // Assert
        assertEquals(testPet.price, 8); // Dog's price should be 1 * age + 1 * rating so 8

    }
}
