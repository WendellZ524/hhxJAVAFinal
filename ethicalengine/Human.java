package ethicalengine;

/**
 * COMP90041, Sem2, 2020: Final Project:
 * The class extends from Persona, adding pregnant, profession setting and
 * if You are in the simulation setting.
 *
 * @author: HAIXIANG HUANG
 * student id: 965205
 * username: HAIXIANGH
 * Github repository link:
 * https://github.com/COMP90041/final-project-moral-machines-haixiangh.git
 */

public class Human extends Persona {
    private Profession profession = Profession.NONE;
    private boolean isPregnant = false;
    private boolean isYou = false;

    /**
     * The setter for profession for a human
     *
     * @param profession the enum type of profession
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    /**
     * The getter for profession. The default is NONE
     * Only adult has profession
     *
     * @return the enum type of profession
     */
    public Profession getProfession() {
        // preset all human's profession to NONE
        Profession realProfession = Profession.NONE;
        if (getAgeCategory().toString().equals("ADULT")) {
            realProfession = this.profession;
        }
        return realProfession;
    }

    /**
     * The getter for pregnant condition
     * Only female human can get pregnant. If illegal, then reset to false.
     *
     * @return the state if a female is pregnant
     */
    public boolean isPregnant() {
        // only female can get pregnant
        if (this.getGender().toString().equals("MALE") ||
                this.getGender().toString().equals("UNKNOWN")) {
            this.isPregnant = false;
        }
        return isPregnant;
    }

    /**
     * The setter for pregnant.
     * The validity check is done by the getter.
     *
     * @param pregnant the boolean if a human is pregnant. Default is false.
     */
    public void setPregnant(boolean pregnant) {
        this.isPregnant = pregnant;
    }

    /**
     * The getter indicating if you are in the simulation.
     *
     * @return s a boolean indicating whether the user is in the simulation, e.g., you
     * are one of the passengers in the car.
     */
    public boolean isYou() {
        return isYou;
    }

    /**
     * The setter for a user, to put the user into the simulation.
     *
     * @param isYou if True, then the user is set in the simulation.
     */
    public void setAsYou(boolean isYou) {
        // set the boolean value to !
        this.isYou = isYou;
    }

    /**
     * The concrete constructor for human
     *
     * @param age        the age of a human.
     * @param profession the enum type of a human's profession.
     * @param gender     the enum type of a human's gender.
     * @param bodytype   the enum type of a human's bodyType
     * @param isPregnant the boolean indicating if the human is pregnant
     */
    public Human(int age, Profession profession, Gender gender, BodyType bodytype, boolean isPregnant) {
        super.setAge(age);
        super.setBodyType(bodytype);
        super.setGender(gender);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }

    /**
     * The constructor for human that does not containing profession and pregnant state.
     *
     * @param age      The age of a human
     * @param gender   the enum type of a human's gender
     * @param bodytype the enum type of a human's bodyType
     */
    public Human(int age, Gender gender, BodyType bodytype) {
        super.setAge(age);
        super.setBodyType(bodytype);
        super.setGender(gender);
    }

    /**
     * The empty constructor for human
     */
    public Human() {
        super();
    }

    /**
     * The copy constructor for human
     *
     * @param otherHuman an human instance
     */
    public Human(Human otherHuman) {
        this.setAge(otherHuman.getAge());
        this.setBodyType(otherHuman.getBodyType());
        this.setGender(otherHuman.getGender());
    }

    /**
     * The getter to get a human's age category
     *
     * @return the enum type of age category
     */
    public AgeCategory getAgeCategory() {
        AgeCategory ageCategory = null;
        int age = super.getAge();
        if (age >= 0 && age <= 4) {
            ageCategory = AgeCategory.BABY;
        } else if (age >= 5 && age <= 16) {
            ageCategory = AgeCategory.CHILD;
        } else if (age >= 17 && age <= 68) {
            ageCategory = AgeCategory.ADULT;
        } else if (age >= 69) {
            ageCategory = AgeCategory.SENIOR;
        }
        return ageCategory;
    }

    /**
     * @return output a human's attributes.
     * If You, Pregnant, or profession is NONE, then do not contain that information.
     */
    public String toString() {
        String temp1;
        String temp2;
        String outputPregnant = "";
        String outputYou = "";
        if (this.isPregnant()) {
            outputPregnant = "pregnant";
        }
        if (this.isYou()) {
            outputYou = "you ";
        }

        if (this.getProfession().toString().equals("NONE")) { //don't print profession if it's NONE
            temp1 = (outputYou + getBodyType().toString().toLowerCase() + " " +
                    getAgeCategory().toString().toLowerCase() + " "
                    + " " + getGender().toString().toLowerCase()
                    + " " + outputPregnant);
            temp1 = temp1.replaceAll("\\s+", " ");
            return temp1.trim();
        } else {
            temp2 = (outputYou + getBodyType().toString().toLowerCase() + " "
                    + getAgeCategory().toString().toLowerCase() + " "
                    + getProfession().toString().toLowerCase() + " "
                    + getGender().toString().toLowerCase()
                    + " " + outputPregnant);
            temp2 = temp2.replaceAll("\\s+", " ");
            return temp2.trim();
        }
    }
}



