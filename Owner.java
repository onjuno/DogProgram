// Olle Nordlander - olno8946


public class Owner {



    private String name;

    private DogList dogs = new DogList();



    public Owner(String name){
        this.name = name;
    }



    public void setDog(Dog dog){
        if (dog.hasOwner() && !dog.hasCurrentOwner(this)){
            return;
        }
        dog.setOwner(this);
        dogs.addDog(dog);

    }

    public void removeTheDog(Dog dog) {
       if(dog != null && dog.hasCurrentOwner(this)){
           dogs.removeDog(dog);
           dog.resetOwner();
        }
    }

    public boolean ownsDog(Dog dog){
        return dogs.doesDogExist(dog);
    }

    public void printOwnedDogs() {
        dogs.printDogsName();
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }
}














