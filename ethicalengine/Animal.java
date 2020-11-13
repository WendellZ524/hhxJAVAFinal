package ethicalengine;

/**
 * COMP90041, Sem2, 2020: Final Project:
 * The class extends from Persona, adding species as unique attributes to animal.
 *
 * @author: HAIXIANG HUANG
 * student id: 965205
 * username: HAIXIANGH
 * Github repository link:
 * https://github.com/COMP90041/final-project-moral-machines-haixiangh.git
 */
public class Animal extends Persona {
    private String species;
    private boolean isPet = false;

    /**
     * The getter of animal species
     *
     * @return the string type of animal species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * the setter for setting the species of an animal
     *
     * @param species the string type of an animal specie
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * The getter indicating if the animal is a pet
     *
     * @return a boolean indicating if the animal is a pet
     */
    public boolean isPet() {
        return isPet;
    }

    /**
     * The setter for setting an animal to be a pet
     *
     * @param isPet Default is false. If True, then the animal is set to be a pet.
     */
    public void setPet(boolean isPet) {
        this.isPet = isPet;
    }

    /**
     * The constructor containing the animal's species.
     *
     * @param species the animal's species.
     */
    public Animal(String species) {
        this.species = species;
    }

    /**
     * The empty constructor for animal
     */
    public Animal() {
        super();
    }

    /**
     * The copy constructor for animal
     *
     * @param otherAnimal an animal instance
     */
    public Animal(Animal otherAnimal) {
        this.setAge(otherAnimal.getAge());
        this.setBodyType(otherAnimal.getBodyType());
        this.setGender(otherAnimal.getGender());
    }

    /**
     *
     * @return output the animal's species and if it is a pet.
     */
    @Override
    public String toString() {
        if (this.isPet) {
            return this.getSpecies().toLowerCase() + " is pet";
        } else {
            return this.getSpecies().toLowerCase();
        }
    }


}

