package ethicalengine;

import java.util.*;

public class ScenarioGenerator {
    private int passengerCountMin = 0;
    private int passengerCountMax = 0;
    private int pedestrianCountMin = 0;
    private int pedestrianCountMax = 0;
    private String[] speciesList = {"bird", "dog", "cat", "turtle",
            "cow", "pig", "deer", "elephant"};

    Random random = new Random();

    public ScenarioGenerator() {
    }

    public ScenarioGenerator(long seed) {
    }

    public ScenarioGenerator(long seed, int passengerCountMinimum,
                             int passengerCountMaximum,
                             int pedestrianCountMinimum,
                             int pedestrianCountMaximum) {
    }

    public void setPassengerCountMin(int min) {
        this.passengerCountMin = min;
    }

    public void setPassengerCountMax(int max) {
        this.passengerCountMax = max;
    }

    public void setPedestrianCountMin(int min) {
        this.pedestrianCountMin = min;

    }

    public void setPedestrianCountMax(int max) {
        this.pedestrianCountMax = max;
    }

    public Animal getRandomAnimal() {
        int ageNum = random.nextInt(120);
        int speciesNum = random.nextInt(speciesList.length);
        int genderNum = random.nextInt(3);
        boolean petBoolean = random.nextBoolean();
        int bodyNum = random.nextInt(Persona.BodyType.values().length);

        String randomSpecies = speciesList[speciesNum];
        Animal animal = new Animal();
        animal.setAge(ageNum);
        animal.setSpecies(randomSpecies);
        animal.setPet(petBoolean);
        // create an enum array of gender
        Persona.Gender[] genderArr = Persona.Gender.values();
        animal.setGender(genderArr[genderNum]);
        // create an enum array of bodytype
        Persona.BodyType[] bodyArr = Persona.BodyType.values();
        animal.setBodyType(bodyArr[bodyNum]);
        return animal;
    }

    public Human getRandomHuman() {
        int ageNum = random.nextInt(120);
        int genderNum = random.nextInt(3);
        boolean pregnantBoolean = random.nextBoolean();
        int bodyNum = random.nextInt(Persona.BodyType.values().length);
        int professionNum = random.nextInt(Persona.Profession.values().length);

        Human human = new Human();
        human.setAge(ageNum);
        human.setPregnant(pregnantBoolean);
        // create an enum array of gender
        Persona.Gender[] genderArr = Persona.Gender.values();
        human.setGender(genderArr[genderNum]);
        // create an enum array of bodytype
        Persona.BodyType[] bodyArr = Persona.BodyType.values();
        human.setBodyType(bodyArr[bodyNum]);
        // create an enum array of profession
        Persona.Profession[] professionArr = Persona.Profession.values();
        human.setProfession(professionArr[professionNum]);
        return human;
    }

    public Scenario generate() {
        // give default max and min number if they are not set
        // or if Min > Max, the Min number is reset to 1
        if (passengerCountMin == 0 || passengerCountMin>passengerCountMax) {
            passengerCountMin = 1;
        }
        if (passengerCountMax == 0) {
            passengerCountMax = 5;
        }
        if (pedestrianCountMin == 0|| pedestrianCountMin>pedestrianCountMax) {
            pedestrianCountMin = 1;
        }
        if (pedestrianCountMax == 0) {
            pedestrianCountMax = 5;
        }

        int passengerNum, pedestrianNum;
        //generate random num from range [passengerCountMin, passengerCountMax]
        passengerNum = random.nextInt(passengerCountMax - passengerCountMin + 1) + passengerCountMin;
        pedestrianNum = random.nextInt(pedestrianCountMax - pedestrianCountMin + 1) + pedestrianCountMin;
        //create arrays
        Persona[] passenger = new Persona[passengerNum];
        Persona[] pedestrian = new Persona[pedestrianNum];

        //random set number of human and animal in given range
        int randomPassengerHumanNum, randomPassengerAnimalNum,
                randomPedsHumanNum, randomPedsAnimalNum;
        randomPassengerHumanNum = random.nextInt(passengerNum + 1);
        randomPassengerAnimalNum = passengerNum - randomPassengerHumanNum;
        randomPedsHumanNum = random.nextInt(pedestrianNum + 1);
        randomPedsAnimalNum = pedestrianNum - randomPedsHumanNum;

        // generate green or red light (green is legal)
        boolean greenLight = random.nextBoolean();

        //还没有设置setAsYou的功能

        // generate a scenario object
        Scenario s = new Scenario(passenger, pedestrian, greenLight);
        // add objects to passenger
        for (int i = 0; i < randomPassengerHumanNum; i++) {
            passenger[i] = getRandomHuman();
        }
        for (int i = randomPassengerHumanNum; i < passenger.length; i++) {
            passenger[i] = getRandomAnimal();
        }
        // add objects to pedestrian
        for (int i = 0; i < randomPedsHumanNum; i++) {
            pedestrian[i] = getRandomHuman();
        }
        for (int i = randomPedsHumanNum; i < pedestrian.length; i++) {
            pedestrian[i] = getRandomAnimal();
        }
        return s;
    }

}

