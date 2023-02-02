/**
 * @author Olle Nordlander - olno8946
 *
 */
public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    private static final double DACHSHUND_TAIL_DIVIDER = 10.0;
    private String name;
    private String breed;
    private int age;
    private int weight;

    private Owner owner;


    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int increaseAge() {
        return age++;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if(hasOwner() || owner == null) {
            return;
        }
        this.owner = owner;
        owner.setDog(this);
    }


    public boolean hasOwner() {
       return owner != null;
    }
    public boolean hasCurrentOwner(Owner theOwner) {
        if(hasOwner()){
            return owner.getName().equalsIgnoreCase(theOwner.getName());
        } else {
            return false;
        }
    }



    public double getTailLength() {
        if (breed.equalsIgnoreCase("Dachshund") || breed.equalsIgnoreCase("tax")) {
            return DACHSHUND_TAIL_LENGTH;
        } else {
            return (double)age * (double)weight / DACHSHUND_TAIL_DIVIDER;
        }

    }

    public String toString() {
        return name + " " + breed + " " + age + " " + weight + " " + getTailLength();
    }

    public void resetOwner() {
        this.owner = null;
    }
}













