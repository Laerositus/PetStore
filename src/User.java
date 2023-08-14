public class User {
    String firstName;
    String lastName;
    String emailAddress;
    int budget;

    /**
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param budget
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
