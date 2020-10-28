package ethicalengine;

public class Human extends Persona {
    private Profession profession;
    private boolean isPregnant = false;

    public Human(int age, Profession profession, Gender gender, Bodytype bodytype, boolean isPregnant) {
        super.setAge(age);
    }

    public Human() {
        super();
    }

}