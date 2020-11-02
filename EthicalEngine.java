import ethicalengine.*;

/**
 * COMP90041, Sem2, 2020: Final Project: A skeleton code for you to update
 *
 * @author: tilman.dingler@unimelb.edu.au
 */
public class EthicalEngine {

    public enum Decision {PASSENGERS, PEDESTRIANS}

    ;

    /**
     * Decides whether to save the passengers or the pedestrians
     *
     * @param Scenario scenario: the ethical dilemma
     * @return Decision: which group to save
     */
    public static Decision decide(Scenario scenario) {
        // a rather random decision engine
        // TOOD: take into account at least 5 scenario characteristics
        Double passengerWeight = 0.5;
        Double pedestrianWeight = 0.5;
        // if the peds are crossing illegally
        if (!scenario.isLegalCrossing()) {
            pedestrianWeight -= 0.5;
        }
        // calculate weights in passengers
        for (Persona i : scenario.getPassengers()) {
            if (i instanceof Human) {
                // if you are in the car as a passenger
                if (((Human) i).isYou()) {
                    passengerWeight += 0.4;
                }
                if (((Human) i).isPregnant()) {
                    passengerWeight += 0.3;
                }
                if (((Human) i).getProfession().toString().equals("CRIMINAL")) {
                    passengerWeight -= 0.5;
                }
                if (((Human) i).getProfession().toString().equals("UNEMPLOYED") ||
                        ((Human) i).getProfession().toString().equals("HOMELESS")) {
                    passengerWeight -= 0.1;
                }
                if (((Human) i).getAgeCategory().toString().equals("BABY") ||
                        ((Human) i).getAgeCategory().toString().equals("CHILD")) {
                    passengerWeight += 0.3;
                }
                if (i.getBodyType().toString().equals("ATHLETIC")) {
                    passengerWeight -= 0.2;
                }
            }
            // animal in the car
            else if (i instanceof Animal) {
                if (((Animal) i).isPet()) {
                    passengerWeight += 0.05;
                }
            }
        }

        // calculate the weights in pedestrians
        for (Persona i : scenario.getPedestrians()) {
            if (i instanceof Human) {
                if (((Human) i).getProfession().toString().equals("CRIMINAL")) {
                    pedestrianWeight -= 0.5;
                }
                if (((Human) i).getProfession().toString().equals("UNEMPLOYED") ||
                        ((Human) i).getProfession().toString().equals("HOMELESS")) {
                    pedestrianWeight -= 0.2;
                }
                if (((Human) i).isPregnant()) {
                    pedestrianWeight += 0.4;
                }
                if (((Human) i).getAgeCategory().toString().equals("BABY") ||
                        ((Human) i).getAgeCategory().toString().equals("CHILD")) {
                    pedestrianWeight += 0.3;
                }
                if (i.getBodyType().toString().equals("ATHLETIC")) {
                    pedestrianWeight -= 0.1;
                }
            }
            // animal on the road
            else if(i instanceof Animal){
                if (((Animal) i).isPet()) {
                    pedestrianWeight += 0.05;
                }
                if (((Animal) i).getSpecies().equals("bird")){
                    pedestrianWeight -=0.05;
                }
            }

        }
        System.out.println("passenger:" + passengerWeight);
        System.out.println("peds:" + pedestrianWeight);
        if (passengerWeight > pedestrianWeight) {
            return Decision.PASSENGERS;
        } else {
            return Decision.PEDESTRIANS;
        }
    }


    public static void main(String[] args) {
        Human passenger1 = new Human(34, Human.Profession.CRIMINAL, Persona.Gender.MALE, Persona.BodyType.ATHLETIC, false);
        Human passenger2 = new Human(28, Human.Profession.CRIMINAL, Persona.Gender.FEMALE, Persona.BodyType.OVERWEIGHT, true);
        Human passenger3 = new Human(55, Human.Profession.NONE, Persona.Gender.FEMALE, Persona.BodyType.ATHLETIC, false);
        Human baby = new Human(1, Human.Profession.CEO, Persona.Gender.FEMALE, Persona.BodyType.OVERWEIGHT, false);
        passenger1.setAsYou(true);

        Human pedestrian1 = new Human(66, Human.Profession.UNEMPLOYED, Persona.Gender.MALE, Persona.BodyType.OVERWEIGHT, false);
        Human pedestrian2 = new Human(19, Human.Profession.NONE, Persona.Gender.FEMALE, Persona.BodyType.ATHLETIC, false);
        pedestrian2.setAsYou(true);

        Animal a1 = new Animal("bird");
        Animal a2 = new Animal("dog");
        a1.setPet(true);

        Persona[] passengers = new ethicalengine.Persona[4];
        passengers[0] = a1;
        passengers[1] = passenger2;
        passengers[2] = passenger1;
        passengers[3] = a2;
        Persona[] pedestrians = new ethicalengine.Persona[4];
        pedestrians[0] = pedestrian1;
        pedestrians[1] = pedestrian2;
        pedestrians[2] = a1;
        pedestrians[3] = pedestrian2;
        Scenario s1 = new Scenario(passengers, pedestrians, false);

        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        scenarioGenerator.setPassengerCountMin(3);
        scenarioGenerator.setPassengerCountMax(8);
        scenarioGenerator.setPedestrianCountMin(6);
        scenarioGenerator.setPedestrianCountMax(10);
        Scenario S=scenarioGenerator.generate();

        System.out.println(S);





    }
}