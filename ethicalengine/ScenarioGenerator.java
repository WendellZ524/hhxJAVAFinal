package ethicalengine;

import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;

public class ScenarioGenerator {
    private long seed=0;
    private int passengerCountMin = 0;
    private int passengerCountMax = 0;
    private int pedestrianCountMin = 0;
    private int pedestrianCountMax = 0;
    private String[] speciesList = {"bird", "dog", "cat", "turtle",
            "cow", "pig", "deer", "elephant"};


    public ScenarioGenerator() {
    }

    public ScenarioGenerator(long seed) {
        this.seed=seed;
    }

    public ScenarioGenerator(long seed, int passengerCountMinimum,
                             int passengerCountMaximum,
                             int pedestrianCountMinimum,
                             int pedestrianCountMaximum) {
        this.seed=seed;
        this.passengerCountMin=passengerCountMinimum;
        this.passengerCountMax=passengerCountMaximum;
        this.pedestrianCountMin=pedestrianCountMinimum;
        this.pedestrianCountMax=pedestrianCountMaximum;
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

    /**
     * using the random number as parameter to create animal instance
     * @param speciesNum the random number (index) of speciesList
     * @param ageNum the random number of age
     * @param genderNum the random number of gender
     * @param petBoolean the random boolean value indicating if it is pet
     * @param bodyNum the random number (index) for setting the bodyType
     * @return a newly created animal instance
     */
    public Animal createRandomAnimal(int speciesNum, int ageNum,int genderNum, boolean petBoolean,
                                   int bodyNum){
        String randomSpecies = speciesList[speciesNum];
        Animal animal = new Animal();
        animal.setAge(ageNum);
        animal.setSpecies(randomSpecies);
        animal.setPet(petBoolean);
        // create an enum array of gender
        Persona.Gender[] genderArr = Persona.Gender.values();
        animal.setGender(genderArr[genderNum]);
        // create an enum array of bodyType
        Persona.BodyType[] bodyArr = Persona.BodyType.values();
        animal.setBodyType(bodyArr[bodyNum]);
        return animal;
    }

    public Animal getRandomAnimal() {
        // if the seed has not been set
        if(seed==0) {
            Random random = new Random();
            int ageNum = random.nextInt(120);
            int speciesNum = random.nextInt(speciesList.length);
            int genderNum = random.nextInt(3);
            boolean petBoolean = random.nextBoolean();
            int bodyNum = random.nextInt(Persona.BodyType.values().length);
            // create animal object using random numbers
            return createRandomAnimal(speciesNum,ageNum,genderNum,petBoolean,bodyNum);
        }
        // the seed is set
        else {
            Random randomWithSeed=new Random(seed);
            int ageNum = randomWithSeed.nextInt(120);
            int speciesNum = randomWithSeed.nextInt(speciesList.length);
            int genderNum = randomWithSeed.nextInt(3);
            boolean petBoolean = randomWithSeed.nextBoolean();
            int bodyNum = randomWithSeed.nextInt(Persona.BodyType.values().length);
            // create animal object using random numbers
            return createRandomAnimal(speciesNum,ageNum,genderNum,petBoolean,bodyNum);
        }


    }

    /**
     * using the random number as parameter to create human instance
     * @param ageNum the random number of age
     * @param genderNum the random number of gender
     * @param pregnantBoolean the random boolean for if she is pregnant
     * @param bodyNum the random number (index) for setting the bodyType
     * @param professionNum the random number (index) for setting the profession
     * @return a human instance
     */
    public Human createRandomAHuman(int ageNum, int genderNum, boolean pregnantBoolean, int bodyNum,
                                    int professionNum){
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

    public Human getRandomHuman() {
        // if the seed has not been set
        if(seed==0) {
            Random random = new Random();
            int ageNum = random.nextInt(120);
            int genderNum = random.nextInt(3);
            boolean pregnantBoolean = random.nextBoolean();
            int bodyNum = random.nextInt(Persona.BodyType.values().length);
            int professionNum = random.nextInt(Persona.Profession.values().length);
            return createRandomAHuman(ageNum,genderNum,pregnantBoolean,bodyNum,professionNum);
        }
        else {
            Random randomWithSeed=new Random(seed);
            int ageNum = randomWithSeed.nextInt(120);
            int genderNum = randomWithSeed.nextInt(3);
            boolean pregnantBoolean = randomWithSeed.nextBoolean();
            int bodyNum = randomWithSeed.nextInt(Persona.BodyType.values().length);
            int professionNum = randomWithSeed.nextInt(Persona.Profession.values().length);
            return createRandomAHuman(ageNum,genderNum,pregnantBoolean,bodyNum,professionNum);
        }


    }

    /**
     *  using the random number as parameter to create Scenario
     * @param passenger the passenger array generated by random number size
     * @param pedestrian the pedestrian array generated by random number size
     * @param greenLight whether the light is green or not (green is legal)
     * @param randomPassengerHumanNum the number of human in number of passengers
     * @param randomPedsHumanNum the number of human in number of pedestrians
     * @return a newly created Scenario instance
     */
    public Scenario createScenario(Persona[] passenger, Persona[] pedestrian, boolean greenLight,
                                   int randomPassengerHumanNum, int randomPedsHumanNum) {
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
        // if the seed has not been set
        if(seed==0) {
            Random random = new Random();
            //generate random num from range [passengerCountMin, passengerCountMax]
            passengerNum = random.nextInt(passengerCountMax - passengerCountMin + 1)
                    + passengerCountMin;
            pedestrianNum = random.nextInt(pedestrianCountMax - pedestrianCountMin + 1)
                    + pedestrianCountMin;
            //create arrays
            Persona[] passenger = new Persona[passengerNum];
            Persona[] pedestrian = new Persona[pedestrianNum];

            //random set number of human and animal in given range
            int randomPassengerHumanNum, randomPedsHumanNum;
            randomPassengerHumanNum = random.nextInt(passengerNum + 1);
            randomPedsHumanNum = random.nextInt(pedestrianNum + 1);

            // generate green or red light (green is legal)
            boolean greenLight = random.nextBoolean();
            return createScenario(passenger, pedestrian, greenLight,
                    randomPassengerHumanNum, randomPedsHumanNum);
        }
        else {
            Random randomWithSeed=new Random(seed);
            //generate random num from range [passengerCountMin, passengerCountMax]
            passengerNum = randomWithSeed.nextInt(passengerCountMax - passengerCountMin + 1)
                    + passengerCountMin;
            pedestrianNum = randomWithSeed.nextInt(pedestrianCountMax - pedestrianCountMin + 1)
                    + pedestrianCountMin;
            //create arrays
            Persona[] passenger = new Persona[passengerNum];
            Persona[] pedestrian = new Persona[pedestrianNum];

            //random set number of human and animal in given range
            int randomPassengerHumanNum, randomPedsHumanNum;
            randomPassengerHumanNum = randomWithSeed.nextInt(passengerNum + 1);
            randomPedsHumanNum = randomWithSeed.nextInt(pedestrianNum + 1);

            // generate green or red light (green is legal)
            boolean greenLight = randomWithSeed.nextBoolean();
            return createScenario(passenger, pedestrian, greenLight,
                    randomPassengerHumanNum, randomPedsHumanNum);
        }

            //还没有设置setAsYou的功能


    }

}

