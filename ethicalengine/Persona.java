package ethicalengine;

/**
 * COMP90041, Sem2, 2020: Final Project:
 * The abstract class contains relevant attributes related to animal (including human).
 *
 * @author: HAIXIANG HUANG
 * student id: 965205
 * username: HAIXIANGH
 * Github repository link:
 * https://github.com/COMP90041/final-project-moral-machines-haixiangh.git
 */

public abstract class Persona {
    private int age = 18;
    private Gender gender;
    private BodyType bodyType;

    /**
     * The getter for age. We assume age lager than 100 or less than 0 is not acceptable
     * Which will be changed to 100 or 0 respectively.
     *
     * @return an int age
     */
    public int getAge() {
        if (age > 100) {
            age = 100;
        } else if (age < 0) {
            age = 0;
        }
        return age;
    }

    /**
     * The setter for age
     *
     * @param age the age we want to set, which can be any int.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * The getter for gender
     *
     * @return the enum type of gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * The setter for gender
     *
     * @param gender the enum type of gender we want to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * The getter for enum bodyType. Default is UNSPECIFIED.
     *
     * @return the enum type of bodyType
     */
    public BodyType getBodyType() {
        return bodyType;
    }

    /**
     * The setter for bodyType
     *
     * @param bodyType the enum type of bodyType
     */
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }


    /**
     * The empty constructor for persona
     */
    public Persona() {
    }

    /**
     * The constructor with parameters
     *
     * @param age      the age of the animal
     * @param gender   the gender of the animal
     * @param bodyType the bodyType of the animal
     */
    public Persona(int age, Gender gender, BodyType bodyType) {
        // age is the invariant which always age>=0
        this.age = age;
        this.gender = gender;
        this.bodyType = bodyType;
    }

    /**
     * The copy constructor of Persona
     *
     * @param otherPersona the instance of Persona
     */
    public Persona(Persona otherPersona) {
        this.age = otherPersona.age;
        this.gender = otherPersona.gender;
        this.bodyType = otherPersona.bodyType;
    }

    /**
     * The enumeration of AgeCategory, which relates to age
     */
    public enum AgeCategory {
        BABY, CHILD, ADULT, SENIOR
    }

    /**
     * The enumeration of Profession, which is specific for human.
     */
    public enum Profession {
        DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, NONE, TEACHER, CLEANER
    }

    /**
     * The enumeration of Gender
     */
    public enum Gender {
        MALE, FEMALE, UNKNOWN;
    }

    /**
     * The enumeration of BodyType
     */
    public enum BodyType {
        AVERAGE, ATHLETIC, OVERWEIGHT, UNSPECIFIED
    }

    /**
     * The abstract method of ToString for Human and Animal
     */
    public abstract String toString();
}




