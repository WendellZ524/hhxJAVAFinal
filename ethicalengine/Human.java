package ethicalengine;

public class Human extends Persona {
    private Profession profession=Profession.NONE;
    private boolean isPregnant = false;

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public Human(int age, Profession profession, Gender gender, Bodytype bodytype, boolean isPregnant) {
        super.setAge(age);
        super.setBodytype(bodytype);
        super.setGender(gender);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }

    public Human() {
        super();
    }

    public Human(Human otherHuman) {
        this.setAge(otherHuman.getAge());
        this.setBodytype(otherHuman.getBodytype());
        this.setGender(otherHuman.getGender());
    }


    public AgeCategory getAgeCategory() {
        AgeCategory ageCategory=null;
        int age = super.getAge();
        if (age >= 0 && age <= 4) {
            ageCategory= AgeCategory.BABY;
        }
        else if (age >= 5 && age <= 16) {
            ageCategory= AgeCategory.CHILD;
        }
        else if (age >= 17 && age <= 68) {
            ageCategory= AgeCategory.ADULT;
        }
        else if (age >= 69) {
            ageCategory= AgeCategory.SENIOR;
        }
    return ageCategory;
    }

    public enum Profession {
        DOCTOR,CEO,CRIMINAL,HOMELESS,UNEMPLOYED,NONE
    }

    public Profession getProfession() {
        return this.profession;
    }
}






