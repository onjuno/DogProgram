// Olle Nordlander - olno8946
import java.util.ArrayList;
import java.util.Collections;

public class DogProgram {

    private ArrayList<Dog> dogs = new ArrayList<>();
    private InputReader userInput = new InputReader();

    private ArrayList<Owner> owners = new ArrayList<>();


    public static void main(String[] args) {
        DogProgram dogProgram = new DogProgram();
        dogProgram.startProgram();

    }

    private void startProgram() {
        DogProgram dogProgram = this;
        while (true) {
            String command = dogProgram.inputFromUser();
            if (command.equalsIgnoreCase("exit")) {
                return;
            }
            dogProgram.userCommand(command);
        }
    }

    private String inputFromUser() {
        printMenu();
        return userInput.getString("Please input command");
    }

    private void printMenu() {
        System.out.println("Welcome!");
        System.out.println("Please select your command:");
        System.out.println("* register new dog\n* list dogs \n* increase age\n* remove dog\n* register new owner\n* give dog");
        System.out.println("* list owners\n* removed owned dog\n* remove owner\n* exit");
    }

    private void userCommand(String command) {
            switch (command) {
                case "register new dog":
                    addDog();
                    break;

                case "list dogs":
                    sortDogs();
                    printDogs();
                    break;

                case "increase age":
                    increaseDogAge();
                    break;

                case "remove dog":
                    removeDog();
                    break;

                case "register new owner":
                    addOwner();
                    break;

                case "give dog":
                    giveDog();
                    break;

                case "list owners":
                    listOwners();
                    break;

                case "remove owned dog":
                    removeOwnedDog();
                    break;

                case "remove owner":
                    deleteOwner();
                    break;

                default:
                   System.out.println("Error, no such command");
            }
        }

    private void addDog(){
        Dog addDog = newDog();
        dogs.add(addDog);
    }

    private Dog newDog(){
        String name = userInput.getString("Name");
        while(name.isEmpty()) {
            System.out.println("Error, please input name?>");
            name = userInput.getString("Name");
        }
        String breed = userInput.getString("Breed");
        while(breed.isEmpty()) {
            System.out.println("Error, please input breed?>");
            breed = userInput.getString("Breed");
        }

        int age = userInput.getInteger("Age");
        while(age < 0) {
            System.out.println("Error, please input age?>");
            age = userInput.getInteger("Age");
        }
        int weight = userInput.getInteger("Weight");
        while(weight <= 0) {
            System.out.println("Error, please input weight?>");
            weight = userInput.getInteger("Weight");
        }

        return new Dog(name, breed, age, weight);

    }

    private void listOwners() {
        if (owners.isEmpty()){
            System.out.println("Error, no owners in register");
            return;
        }
        for (Owner owner : owners) {
            System.out.println(owner);
            owner.printOwnedDogs();
        }
    }

    private void printDogs() {
        if (dogs.size() > 0) {
            double lengthTail = userInput.getDouble("Smallest tail length to display");
            while (lengthTail < 0.0) {
                System.out.println("Error, please input tail length?>");
                lengthTail = userInput.getDouble("Smallest tail length to display");
            }
            int dogsFound = 0;
            for (int i = 0; i < dogs.size(); i++) {
                if (dogs.get(i).getTailLength() >= lengthTail) {
                    System.out.println(dogs.get(i));
                    dogsFound++;
                    if(dogs.get(i).hasOwner()){
                        System.out.println(", owned by " + dogs.get(i).getOwner());
                    }
                }
            }
            if (dogsFound == 0) {
                System.out.println("Error, no dog have a tail that long");
            }
        } else {
            System.out.println("Error, no dogs in register");
        }
    }

    private void increaseDogAge() {
        String whichDog = userInput.getString("Enter the name of the dog");
        for (Dog dog: dogs) {
            if(dog.getName().equalsIgnoreCase(whichDog)) {
                dog.increaseAge();
                System.out.println(dog.getName() + " is now one year older");
                return;
            }
        }
        System.out.println("Error, no such dog");
    }

    private void removeDog() {
        String theDog = userInput.getString("Enter name of the dog");
        while (theDog.isEmpty() || theDog.isBlank()) {
            System.out.println("Error, please input correct dog");
            theDog = userInput.getString("Enter the name of the dog");
        }
        for (Dog dog : dogs) {
            if (dog.getName().equalsIgnoreCase(theDog)) {
                removeOwnedDoge(dog);
                dogs.remove(dog);
                System.out.println(dog.getName() + "is now removed from the register");
                return;
            }
        }
        System.out.println("Error, no such dog.");
    }

    private void removeOwnedDoge(Dog dog){
        if(dog.getOwner() != null){
            dog.getOwner().removeTheDog(dog);
        }
    }

    private void addOwner() {
        Owner added = newOwner();
        owners.add(added);
    }


    private Owner newOwner() {
        String name = userInput.getString("Name");
        while (name.isEmpty()) {
            System.out.println("Error, please input name?>");
            name = userInput.getString("Name");
        }
        return new Owner(name);
    }

    private void giveDog() {
        String str = userInput.getString("Enter the name of the dog");
        while (str.isEmpty() || str.isBlank()) {
            System.out.println("Error, please input correct dog");
            str = userInput.getString("Enter the name of the dog");
        }
        Dog theDog = findDog(str);

        if (theDog != null) {
            if (theDog.hasOwner()) {
                System.out.println("Error, the dog already has an owner");
                return;
            }
            String inputOwner = userInput.getString("Enter the name of the owner");
            while (inputOwner.isEmpty() || inputOwner.isBlank()) {
                System.out.println("Error, please input correct owner.");
                inputOwner = userInput.getString("Enter the name of the owner");
            }
            Owner theOwner = findOwner(inputOwner);
            if (theOwner == null) {
                System.out.println("Error, no such owner");
                return;
            }
            theOwner.setDog(theDog);
            System.out.println(theOwner + " now owns " + theDog);
        } else{
            System.out.println("Error, no dog with that name");
        }
    }

    private Dog findDog(String name) {
        for (Dog dog: dogs) {
            if (dog.getName().equalsIgnoreCase(name)) {
                return dog;
            }
        }
        return null;
    }

    private Owner findOwner(String name) {

        for (Owner finder: owners) {
            if(finder.getName().equalsIgnoreCase(name)) {
                return finder;
            }
        }
        return null;
    }

    private void removeOwnedDog() {
        String str = userInput.getString("Enter the name of the dog");
        while (str.isEmpty() || str.isBlank()) {
            System.out.println("Error, please input correct dog");
            str = userInput.getString("Enter the name of the dog");
        }
        Dog theDog = findDog(str);

        if (theDog != null) {
            if(!theDog.hasOwner()){
                System.out.println("Error: " + theDog + " is not owned by anyone");
                return;
            }
            if (theDog.hasOwner()) {
                Owner theOwner = theDog.getOwner();
                theOwner.removeTheDog(theDog);
                System.out.println(theDog + " is removed " + theOwner);
            }
        } else {
            System.out.println("Error, no dog with that name");
        }
    }
    private void deleteOwner() {
        String str = userInput.getString("Enter the name of the user");
        while (str.isEmpty() || str.isBlank()) {
            System.out.println("Error, please input correct user");
            str = userInput.getString("Enter the name of the user");
        }
        Owner theOwner = findOwner(str);

        if (theOwner != null) {
            deleteOwnersDogs(theOwner);
            owners.remove(theOwner);

            System.out.println(theOwner + " is now removed from the register");
        } else {
            System.out.println("Error, no user with that name");
        }
    }
    private void deleteOwnersDogs(Owner owner) {
        for (int i = dogs.size() - 1; i >= 0; i--) {
            if (dogs.get(i).getOwner() == owner) {
                dogs.remove(dogs.get(i));
            }
        }
    }

    private void dogSwap(int a, int b) {
        Collections.swap(dogs, a, b);
    }

    private int compareTails(int a, int b){
        if (dogs.get(a).getTailLength() > dogs.get(b).getTailLength()) {
            return a;
        } else {
            return b;
        }
    }

    private int compareName(int a, int b){
        if (dogs.get(a).getName().compareToIgnoreCase(dogs.get(b).getName()) > 0) {
            return a;
        } else {
            return b;
        }
    }

    private int compareDogs(int a, int b) {
        if(dogs.get(a).getTailLength() == dogs.get(b).getTailLength()) {
            return compareName(a,b);
        } else {
            return compareTails(a,b);
        }
    }
    private int findSmallestDog(int a) {
        int smallestDog = a;
        for(int i = a + 1; i < dogs.size(); i++) {
            if (compareDogs(smallestDog, i) < i){
                smallestDog = i;
            }
        }
        return smallestDog;
    }

    private void sortDogs() {
        for (int i = 0; i < dogs.size(); i++) {
            if (i != findSmallestDog(i)) {
                dogSwap(findSmallestDog(i), i);
            }
        }
    }

}

