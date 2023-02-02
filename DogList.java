// Olle Nordlander - olno8946


public class DogList {
    private Dog[] dogList = new Dog[10];
    private int count;

    public DogList() {
        this.count = count;
    }

    private void increaseSize() {
        Dog[] dogListEx = new Dog[dogList.length + 1];
        for (int i = 0; i < dogList.length; i++) {
            dogListEx[i] = dogList[i];
        }
        dogList = dogListEx;
    }

    public void addDog(Dog a) {
        if (a != null && !doesDogExist(a)) {
            for (int i = 0; i < dogList.length; i++) {
                if (dogList[i] != null) {
                    count++;
                }
            }
            if (count == dogList.length) {
                increaseSize();
                for (int i = 0; i < dogList.length; i++) {
                    if (dogList[i] == null) {
                        dogList[i] = a;
                        count = 0;
                        return;
                    }
                }
            } else {
                for (int i = 0; i < dogList.length; i++) {
                    if (dogList[i] == null) {
                        dogList[i] = a;
                        count = 0;
                        return;
                    }
                }
            }
        }
    }

    public void removeDog(Dog a) {
        if (a != null) {
            for(int i = 0; i < dogList.length; i++) {
                if(a == dogList[i]) {
                    dogList[i] = null;
                }
            }
        }
    }

    public boolean doesDogExist(Dog a) {
        for(int i=0; i < dogList.length; i++) {
            if (a == dogList[i]) {
                return true;
            }

        }
        return false;
    }

    public void printDogsName() {
        for (int i=0; i < dogList.length; i++)
            if(dogList[i] != null) {
                System.out.println(dogList[i].getName());
            }
    }
}




