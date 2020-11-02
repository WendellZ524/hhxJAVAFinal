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
        long seed1 = 1234;
        long seed2 = 1337;
        ScenarioGenerator scenarioGenerator = new ScenarioGenerator();
        ScenarioGenerator scenarioGenerator2 = new ScenarioGenerator(seed1);
        ScenarioGenerator scenarioGenerator3 = new ScenarioGenerator(seed2, 1, 7, 1, 7);

        // getter
        Human person = scenarioGenerator.getRandomHuman();
        Animal animal = scenarioGenerator3.getRandomAnimal();

        // setter
        scenarioGenerator.setPassengerCountMin(1);
        scenarioGenerator.setPassengerCountMax(7);
        scenarioGenerator.setPedestrianCountMin(1);
        scenarioGenerator.setPedestrianCountMax(7);

        // same seed same scenario features
        ScenarioGenerator tmp = new ScenarioGenerator(seed1);
        Scenario scenario1 = scenarioGenerator2.generate();
        Scenario scenario2 = tmp.generate();
        System.out.println(scenario1);
        System.out.println(scenario2);

        System.out.println (scenario1.toString().equals(scenario2.toString()));







    }
}