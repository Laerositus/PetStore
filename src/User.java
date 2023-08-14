public class User {
    String firstName;
    String lastName;
    String emailAddress;
    int budget;

    /**
     * @param firstName First name of user
     * @param lastName Last name of user
     * @param emailAddress Email address o User
     * @param budget Available Budget of User
     */
    User(String firstName, String lastName, String emailAddress, int budget){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.budget = budget;
    }
    
    public String toString() {
        return String.format("%s %s - %s, Budget: %d \n", this.firstName, this.lastName, this.emailAddress, this.budget);
    }
}
