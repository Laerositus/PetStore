

import java.util.Date;
import java.util.concurrent.TimeUnit;

enum AnimalType {
    Cat, Dog
}

public class Pet {
    User owner;
    String name;
    AnimalType type;
    String description;
    Date dateOfBirth;
    int price;
    Integer rating;

    public Pet(User owner, String name, AnimalType type, String description, Date dateOfBirth, Integer rating) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.rating = rating;

        SetPrice();
    }


    public void SetPrice(){
        // Checking if the animal is a Cat or Dog
        Date now = new Date();
        long ageInMilliseconds = now.getTime() - this.dateOfBirth.getTime();
        long ageInDays = TimeUnit.DAYS.convert(ageInMilliseconds, TimeUnit.MILLISECONDS);
        Integer age = (int) ageInDays / 365;

        if (this.type == AnimalType.Cat)
            this.price = age;
        if (this.type == AnimalType.Dog)
            this.price = age + this.rating;
    }
    
    public String toString(){
        String s = "";
        if(this.owner != null)
            s+= String.format("Owner: %s %s \n", this.owner.firstName, this.owner.lastName);
        else s+= "Owner: none \n";
        s+= String.format("Name: %s \n", this.name);
        s+= String.format("Type: %s \n", this.type);
        s+= String.format("Description: %s \n", this.description);
        s+= String.format("Birth: %s \n", this.dateOfBirth.toString());
        s+= String.format("Price: %s \n", this.price);
        if(this.rating != null)
            s+= String.format("Rating: %s \n", this.rating);
        return s;
    }
}
