import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetStoreTest {

    /**
     * Tests for user list creation
     */
    @Test
    void testCreateUsers() {

        // Act
        ArrayList<User> users = PetStore.createUsers();

        // Assert
        assertFalse(users.isEmpty());
    }

    /**
     * Tests for pet list creation
     */
    @Test
    void testCreatePets() {
        // Arrange

        // Act
        ArrayList<Pet> pets = PetStore.createPets();

        // Assert
        assertFalse(pets.isEmpty());
    }

    /**
     * Tests if at least one pet has been bought
     */
    @Test
    void testBuy() {
        // Arrange
        ArrayList<User> users = PetStore.createUsers();
        ArrayList<Pet> pets = PetStore.createPets();

        // Act
        PetStore.buy(users, pets);

        // Assert
        Optional<Pet> p = pets.stream().filter(pet -> pet.owner != null).findFirst();

        assertTrue(p.isPresent());
    }



}
