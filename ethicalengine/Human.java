package ethicalengine;

public class Human extends Persona {
    private Profession profession = Profession.NONE;
    private boolean isPregnant = false;
    private boolean isYou = false;

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Profession getProfession() {
        Profession realProfession = Profession.NONE;
        // 检查是否只有adult有
        if (getAgeCategory().toString().equals("ADULT")) {
            realProfession = this.profession;
        } else {
            System.out.println("只有adult才有职业");
        }
        return realProfession;
    }

    public boolean isPregnant() {
        // only female can get pregnant
        if (this.getGender().toString().equals("MALE") ||
                this.getGender().toString().equals("UNKNOWN")) {
            this.isPregnant = false;
        }
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        // the pregnant is already valid

        this.isPregnant = pregnant;
    }

    public boolean isYou() {
        return isYou;
    }

    public void setAsYou(boolean isYou) {
        // set the boolean value to !

        this.isYou = isYou;
    }


    public Human(int age, Profession profession, Gender gender, BodyType bodytype, boolean isPregnant) {
        super.setAge(age);
        super.setBodyType(bodytype);
        super.setGender(gender);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }

    public Human(int age, Gender gender, BodyType bodytype) {
        super.setAge(age);
        super.setBodyType(bodytype);
        super.setGender(gender);
    }

    public Human() {
        super();
    }

    public Human(Human otherHuman) {
        this.setAge(otherHuman.getAge());
        this.setBodyType(otherHuman.getBodyType());
        this.setGender(otherHuman.getGender());
    }

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


    public String toString() {
        String outputYou = "";
        String outputPregnant = "";
        if (this.isYou()) {
            outputYou = "you";
        }
        if (this.isPregnant()) {
            outputPregnant = "pregnant";
        }

        if (this.getProfession().toString().equals("NONE")) {
            return (outputYou + " " + getBodyType() + " " + getAgeCategory() + " "
                    + " " + outputPregnant);
        } else {
            return (outputYou + " " + getBodyType() + " " + getAgeCategory() + " "
                    + getProfession() + " " + outputPregnant);
        }
    }


    //tester
//    public static void main(String[] args) {
//        Human passenger1 = new Human(34, Human.Profession.HOMELESS,
//                Gender.FEMALE, Persona.BodyType.ATHLETIC, false);
//        passenger1.setPregnant(true);
//        System.out.println(passenger1);
//    }
}



