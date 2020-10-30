package ethicalengine;

/**
 * Represents a scenario to decide on
 * COMP90041, Sem2, 2020: Final Project
 *
 * @author: tilman.dingler@unimelb.edu.au
 */
public class Scenario {
    private boolean isLegalCrossing;
    private Persona[] passengers;
    private Persona[] pedestrians;
   private boolean inLane=false;


    public Scenario(Persona[] passengers,Persona[] pedestrians, boolean isLegalCrossing) {
        this.passengers = passengers;
        this.pedestrians = pedestrians;
        this.isLegalCrossing = isLegalCrossing;
    }

    public boolean hasYouInCar() {
        boolean InCar = false;
        for (Persona i : passengers) {
            // downcasting
            if (((Human) i).isYou()) {
                InCar = true;
                break;
            }
        }
        return InCar;
    }

    public boolean hasYouInLane() {
        return inLane;
    }


    public Scenario() {

    }

    //tester
//    public static void main(String[] args) {
//        Human passenger1 = new Human(34, Human.Profession.HOMELESS, Persona.Gender.MALE, Persona.BodyType.ATHLETIC, false);
//        Human passenger2 = new Human(28, Human.Profession.CEO, Persona.Gender.FEMALE, Persona.BodyType.OVERWEIGHT, true);
//        passenger2.setAsYou(true);
//        Persona[] passengers = new ethicalengine.Persona[2];
//        passengers[0] = passenger1;
//        passengers[1] = passenger2;
//        Scenario s1 = new Scenario(passengers, false);
//        System.out.println(s1.hasYouInCar());
//        System.out.println(s1);
//
//    }

}