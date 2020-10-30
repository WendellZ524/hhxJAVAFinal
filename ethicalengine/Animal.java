package ethicalengine;

public class Animal extends Persona {
    private String species;
    private boolean isPet = false;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isPet() {
        return isPet();
    }

    public void setPet(boolean isPet) {
        this.isPet = isPet;
    }

    public Animal(String species) {
        this.species = species;
    }

    public Animal() {
        super();
    }

    public Animal(Animal otherAnimal) {
        this.setAge(otherAnimal.getAge());
        this.setBodyType(otherAnimal.getBodyType());
        this.setGender(otherAnimal.getGender());
    }

    @Override
    public String toString() {
        if (this.isPet) {
            return this.getSpecies().toLowerCase() + " is pet" ;
        } else {
            return this.getSpecies().toLowerCase();
        }
    }

    //tester
//    public static void main(String[] args) {
//        Animal a1 = new Animal("bird");
//        a1.setPet(true);
//        System.out.println(a1);
//    }
}

