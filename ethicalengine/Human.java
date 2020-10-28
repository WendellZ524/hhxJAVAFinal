package ethicalengine;

public class Human extends Persona {
    private Profession profession;
    private boolean isPregnant = false;

    public Human(int age, Profession profession, Gender gender, Bodytype bodytype, boolean isPregnant) {
        super.setAge(age);
        super.setBodytype(bodytype);
        super.setGender(gender);
        this.profession=profession;
        this.isPregnant=isPregnant;
    }

    public Human() {
        super();
    }

    public Human(Human otherHuman){
        this.setAge(otherHuman.getAge());
        this.setBodytype(otherHuman.getBodytype());
        this.setGender(otherHuman.getGender());
    }

}