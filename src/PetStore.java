import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PetStore {

    /**
     * Local string for buy history overview
     */
    static String history = "";
    static ArrayList<Pet> pets;
    static ArrayList<User> users;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while(loop){
            System.out.println("What would you like to do?");
            System.out.println("Create a list of users - 'create-users'");
            System.out.println("Create a list of pets - 'create-pets'");
            System.out.println("Show the list of users - 'list-users'");
            System.out.println("Show the list of pets - 'list-pets'");
            System.out.println("Let users buy pets - 'buy");
            System.out.println("Show the history - 'history'");
            System.out.println("Exit application - 'exit'");

            String input = scanner.next();
            switch (input) {
                case "create-users" -> {
                    users = createUsers();
                    listUsers();
                }
                case "create-pets" -> {
                    pets = createPets();
                    listPets();
                }
                case "list-users" -> listUsers();
                case "list-pets" -> listPets();
                case "buy" -> buy(users, pets);
                case "history" -> {
                    if (history.isEmpty()) {
                        System.out.println("No history yet");
                        break;
                    }
                    System.out.println(history);
                }
                case "exit" -> {
                    loop = false;
                    break;
                }
                default -> System.out.println("invalid input");
            }
        }

        System.out.println("Thanks for using the Pet Store! \nGoodbye!");
    }

    /**
     * Tries to buy a pet for every user
     * @param users Users that will buy pets
     * @param pets Pets that can be bought
     */
    public static void buy(ArrayList<User> users, ArrayList<Pet> pets){
        history += String.format("Execution date: %s \n", new Date());
        if (users.isEmpty() || users == null) {
            System.out.println("No current users");
            history += "No users, invalid buy operation";
            return;
        }
        if (pets.isEmpty() || pets == null){
            System.out.println("No pets");
            history += "No pets, invalid buy operation";
            return;
        }

        AtomicInteger successfulTransactions = new AtomicInteger();
        AtomicInteger usersWithoutPet = new AtomicInteger();
        users.forEach(user ->{
            Optional<Pet> toBuy = pets.stream().filter(pet -> user.budget >= pet.price && pet.owner == null).findFirst();
            if(toBuy.isPresent()){
                Pet pet = toBuy.get();
                pet.owner = user;
                user.budget -= pet.price;
                successfulTransactions.getAndIncrement();
            }else
                usersWithoutPet.getAndIncrement();
        });
        history += String.format("Successful transactions: %s \n", successfulTransactions);
        history += String.format("users without pets: %s \n", usersWithoutPet);
        history += "\n";

    }

    /**
     * @return ArrayList of users
     */
    public static ArrayList<User> createUsers(){
        ArrayList<User> users = new ArrayList<>();
        //Using File reading for easy access and editing
        try {
            File names = new File("src\\Names.txt");
            Scanner scanner = new Scanner(names);
            for (int i = 0; i < 10; i++) {
                // Preparing data for adding
                String firstName = scanner.next().trim();
                String lastNameUnedited = scanner.next().trim();
                String lastName = lastNameUnedited.substring(0, lastNameUnedited.length() - 1);
                String email = String.format("%s.%s@email.com",firstName.charAt(0), lastName);
                int budget = ThreadLocalRandom.current().nextInt(0, 13);

                // Randomizing the budget each user has
                users.add(new User(firstName, lastName, email, budget));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return users;
    }

    /**
     * Lists the users in the console
     */
    public static void listUsers(){
        if(users.isEmpty() || users == null){
            System.out.println("No current users");
            return;
        }

        System.out.println("Users:");
        users.forEach(user -> {
            System.out.println(user);
        });
    }

    /**
     * Creates 20 randomized pets
     * @return ArrayList of Pets
     */
    public static ArrayList<Pet> createPets(){
        ArrayList<Pet> pets = new ArrayList<>();

        try {
            File petNames = new File("src\\PetNames.txt");
            Scanner scanner = new Scanner(petNames);

            for(int i = 0; i < 20; i++){

                String nameUnedited = scanner.next().trim();
                String name = nameUnedited.substring(0,nameUnedited.length() -1);

                // Preparing all data for editing
                AnimalType randomAnimalType = AnimalType.values()[ThreadLocalRandom.current().nextInt(0,2)];
                int randomDay = ThreadLocalRandom.current().nextInt(0,31);
                int randomMonth = ThreadLocalRandom.current().nextInt(0,13);
                int randomYear = ThreadLocalRandom.current().nextInt(2000,2023) - 1900;
                Date randomDate = new Date(randomYear, randomMonth, randomDay);
                Integer rating = null;
                if(randomAnimalType == AnimalType.Dog) {
                    rating =  ThreadLocalRandom.current().nextInt(1,11);
                }
                pets.add(new Pet(null, name, randomAnimalType, "A wonderous pet", randomDate, rating));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return pets;
    }

    /**
     * Lists the current pets in the console
     */
    public static void listPets(){
        if(pets.isEmpty() || pets == null){
            System.out.println("No current pets");
            return;
        }
        System.out.println("Pets:");
        pets.forEach(pet -> {
            System.out.println(pet.toString());
        });
    }
    
}
