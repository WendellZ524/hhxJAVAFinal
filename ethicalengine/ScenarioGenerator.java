package ethicalengine;
import java.util.*;

public class ScenarioGenerator {
    private int passengerCountMin=1;
    private int passengerCountMax=5;
    private int pedestrianCountMin=1;
    private int pedestrianCountMax=5;
    private String[] speciesList={"bird","dog","cat","turtle",
            "cow","pig","deer","elephant"};

    Random random = new Random();

    public ScenarioGenerator(){}

    public ScenarioGenerator(long seed){
    }
    public ScenarioGenerator(long seed, int passengerCountMinimum,
                             int passengerCountMaximum,
                             int pedestrianCountMinimum,
                             int pedestrianCountMaximum){
    }
    public void setPassengerCountMin(int min){
        this.passengerCountMin=min;
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

    public boolean randomBoolean(int boolNum){
        if(boolNum==0){
            return false;
        }
        else {
            return true;
        }
    }


    public Animal getRandomAnimal(){
        int ageNum=random.nextInt(120);
        int speciesNum=random.nextInt(speciesList.length);
        int genderNum=random.nextInt(3);
        int petNum=random.nextInt(2);
        int bodyNum=random.nextInt(Persona.BodyType.values().length);

        String randomSpecies=speciesList[speciesNum];
        Animal animal = new Animal();
        animal.setAge(ageNum);
        animal.setSpecies(randomSpecies);
        animal.setPet(randomBoolean(petNum));
        // create an enum array of gender
        Persona.Gender[] genderArr =Persona.Gender.values();
        animal.setGender(genderArr[genderNum]);
        // create an enum array of bodytype
        Persona.BodyType[] bodyArr = Persona.BodyType.values();
        animal.setBodyType(bodyArr[bodyNum]);
        return animal;}

    public Human getRandomHuman(){
        int ageNum=random.nextInt(120);
        int genderNum=random.nextInt(3);
        int pregnantNum=random.nextInt(2);
        int bodyNum=random.nextInt(Persona.BodyType.values().length);
        int professionNum=random.nextInt(Persona.Profession.values().length);

        Human human = new Human();
        human.setAge(ageNum);
        human.setPregnant(randomBoolean(pregnantNum));
        // create an enum array of gender
        Persona.Gender[] genderArr =Persona.Gender.values();
        human.setGender(genderArr[genderNum]);
        // create an enum array of bodytype
        Persona.BodyType[] bodyArr = Persona.BodyType.values();
        human.setBodyType(bodyArr[bodyNum]);
        // create an enum array of profession
        Persona.Profession[] professionArr=Persona.Profession.values();
        human.setProfession(professionArr[professionNum]);
        return human;}

    }

