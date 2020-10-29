package ethicalengine;

public class Human extends Persona {
    private Profession profession=Profession.NONE;
    private boolean isPregnant = false;
    private boolean isYou=false;

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public boolean isPregnant() {
        // only female can get pregnant
        if(getGender().toString().equals("MALE")||getGender().toString().equals("UNKNOWN")){
            isPregnant=false;
        }
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        // the pregnant is already valid
        isPregnant = pregnant;
    }

    public boolean isYou(){
        return isYou;
    }
    public void setAsYou(boolean isYou){
        this.isYou=isYou;
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

    public Profession getProfession() {
        return this.profession;
    }
}


