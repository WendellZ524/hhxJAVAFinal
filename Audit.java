import ethicalengine.*;
import java.util.*;


public class Audit {
    private String auditType = "Unspecified";
    private static ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
    // the hashmap is the aggregate storage of all values to be output
    private static HashMap<String,Double> map =new HashMap<String ,Double>();
    private int roundCounts = 0;
    private int passengerSurviveCounts = 0;
    private double sumAliveHumanAge=0;
    private double aliveHumanAvgAge=0;



    // set up lists for corresponding attributes
    private ArrayList<Integer> maleList = new ArrayList<>();
    private ArrayList<Integer> femaleList = new ArrayList<>();
    private ArrayList<Integer> averageBodyList = new ArrayList<>();
    private ArrayList<Integer> athleticBodyList = new ArrayList<>();
    private ArrayList<Integer> overweightBodyList = new ArrayList<>();
    // lists specifically for human
    private ArrayList<Integer> doctorList = new ArrayList<>();
    private ArrayList<Integer> ceoList = new ArrayList<>();
    private ArrayList<Integer> criminalList = new ArrayList<>();
    private ArrayList<Integer> homelessList = new ArrayList<>();
    private ArrayList<Integer> unemployedList = new ArrayList<>();
    private ArrayList<Integer> teacherList = new ArrayList<>();
    private ArrayList<Integer> cleanerList = new ArrayList<>();
    private ArrayList<Integer> pregnantList = new ArrayList<>();
    private ArrayList<Integer> babyList = new ArrayList<>();
    private ArrayList<Integer> childList = new ArrayList<>();
    private ArrayList<Integer> adultList = new ArrayList<>();
    private ArrayList<Integer> seniorList = new ArrayList<>();
    // lists specifically for animal and related scenarios
    private ArrayList<Integer> animalList = new ArrayList<>();
    private ArrayList<Integer> humanList = new ArrayList<>();
    private ArrayList<Integer> petList = new ArrayList<>();
    private ArrayList<Integer> greenList = new ArrayList<>();
    private ArrayList<Integer> redList = new ArrayList<>();
    //    ArrayList<Integer> passengerNumList = new ArrayList<>();
    private ArrayList<Integer> birdList = new ArrayList<>();
    private ArrayList<Integer> dogList = new ArrayList<>();
    private ArrayList<Integer> catList = new ArrayList<>();
    private ArrayList<Integer> turtleList = new ArrayList<>();
    private ArrayList<Integer> cowList = new ArrayList<>();
    private ArrayList<Integer> pigList = new ArrayList<>();
    private ArrayList<Integer> deerList = new ArrayList<>();
    private ArrayList<Integer> elephantList = new ArrayList<>();

    private double greenSurvive, redSurvive,humanSurvive, animalSurvive, petSurvive, maleSurvive, femaleSurvive, avgBodySurvive, athleticBodySurvive, overweightBodySurvive,
            babySurvive, childSurvive, adultSurvive, seniorSurvive, doctorSurvive, ceoSurvive,
            criminalSurvive, homelessSurvive, unemployedSurvive, teacherSurvive, cleanerSurvive, pregnantSurvive,
            birdSurvive, dogSurvive, catSurvive, turtleSurvive, cowSurvive, pigSurvive, elephantSurvive, deerSurvive;

    private double passengerSurviveRates, pedestrianSurviveRate, greenSurviveRate,redSurviveRate,
            humanSurviveRate, animalSurviveRate, petSurviveRate, maleSurviveRate, femaleSurviveRate, avgBodySurviveRate, athleticSurviveRate,
            overweightBodySurviveRate, babySurviveRate, childSurviveRate, adultSurviveRate, seniorSurviveRate,
            doctorSurviveRate, ceoSurviveRate, criminalSurviveRate, homelessSurviveRate, unemployedSurviveRate,
            teacherSurviveRate, cleanerSurviveRate, pregnantSurviveRate,
            birdSurviveRate, dogSurviveRate, catSurviveRate, turtleSurviveRate, cowSurviveRate, pigSurviveRate, elephantSurviveRate,
            deerSurviveRate;


    public Audit() {
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditType() {
        return auditType;
    }

    public void run(int runs) {
        for (int i = 0; i < runs; i++) {
            ScenarioGenerator s = new ScenarioGenerator();
            scenarioList.add(s.generate());
        }
        roundCounts = runs;
    }

    /**
     * The private method is for adding numbers to attribute lists only for human
     *
     * @param liveFlag comes from Live() after deciding which group live
     * @param human    comes from the scenarioList that contains human objects
     */
    private void addHumanCategoryList(int liveFlag, Human human) {
        if (human.getAgeCategory().toString().equals("BABY")) {
            babyList.add(liveFlag);
        } else if (human.getAgeCategory().toString().equals("CHILD")) {
            childList.add(liveFlag);
        } else if (human.getAgeCategory().toString().equals("ADULT")) {
            adultList.add(liveFlag);
        } else if (human.getAgeCategory().toString().equals("SENIOR")) {
            seniorList.add(liveFlag);
        }

        if (human.getProfession().toString().equals("DOCTOR")) {
            doctorList.add(liveFlag);
        } else if (human.getProfession().toString().equals("CEO")) {
            ceoList.add(liveFlag);
        } else if (human.getProfession().toString().equals("CRIMINAL")) {
            criminalList.add(liveFlag);
        } else if (human.getProfession().toString().equals("HOMELESS")) {
            homelessList.add(liveFlag);
        } else if (human.getProfession().toString().equals("UNEMPLOYED")) {
            unemployedList.add(liveFlag);
        } else if (human.getProfession().toString().equals("TEACHER")) {
            teacherList.add(liveFlag);
        } else if (human.getProfession().toString().equals("CLEANER")) {
            cleanerList.add(liveFlag);
        }
    }

    /**
     * The private method is for adding numbers to attribute lists only for animal
     *
     * @param liveFlag comes from Live() after deciding which group live
     * @param animal   comes from the scenarioList that contains animal objects
     */
    private void addAnimalCategoryList(int liveFlag, Animal animal) {
        if (animal.getSpecies().equals("bird")) {
            birdList.add(liveFlag);
        } else if (animal.getSpecies().equals("dog")) {
            dogList.add(liveFlag);
        } else if (animal.getSpecies().equals("cat")) {
            catList.add(liveFlag);
        } else if (animal.getSpecies().equals("turtle")) {
            turtleList.add(liveFlag);
        } else if (animal.getSpecies().equals("cow")) {
            cowList.add(liveFlag);
        } else if (animal.getSpecies().equals("pig")) {
            pigList.add(liveFlag);
        } else if (animal.getSpecies().equals("deer")) {
            deerList.add(liveFlag);
        } else if (animal.getSpecies().equals("elephant")) {
            elephantList.add(liveFlag);
        }

    }

    /**
     * This method set the flag (0 or 1), which correspondingly represents death or live in each
     * attributes lists
     *
     * @param passengerLive generated from decide method (EthicalEngine) that represents if
     *                      the passenger is alive.
     */
    public void Live(boolean passengerLive) {
        int passengerLiveFlag, pedestrianLiveFlag;
        Persona[] passenger;
        Persona[] pedestrian;

        // passenger live implies pedestrians die, vice versa
        // add counter to count number of cases that passenger survive
        if (passengerLive) {
            passengerSurviveCounts += 1;
            passengerLiveFlag = 1;
            pedestrianLiveFlag = 0;
        } else {
            passengerLiveFlag = 0;
            pedestrianLiveFlag = 1;
        }

        // Firstly, get passenger array in each scenario
        for (Scenario i : scenarioList) {
            // legality check at the same time
            if (i.isLegalCrossing()) {
                greenList.add(1);
                redList.add(0);
            } else {
                greenList.add(0);
                redList.add(1);
            }

            passenger = i.getPassengers();
            for (Persona j : passenger) {
                // find shared persona attributes at the beginning
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(passengerLiveFlag);
                } else if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(passengerLiveFlag);
                }
                if (j.getBodyType().toString().equals("AVERAGE")) {
                    averageBodyList.add(passengerLiveFlag);
                } else if (j.getBodyType().toString().equals("ATHLETIC")) {
                    athleticBodyList.add(passengerLiveFlag);
                } else if (j.getBodyType().toString().equals("OVERWEIGHT")) {
                    overweightBodyList.add(passengerLiveFlag);
                }

                if (j instanceof Human) {
                    humanList.add(passengerLiveFlag);
                    addHumanCategoryList(passengerLiveFlag, (Human) j);
                    if (((Human) j).isPregnant()) {
                        pregnantList.add(passengerLiveFlag);
                    }
                    // calculate alive average people age
                    if(passengerLive){
                        sumAliveHumanAge+=j.getAge();
                    }

                }
                if (j instanceof Animal) {
                    animalList.add(passengerLiveFlag);
                    addAnimalCategoryList(passengerLiveFlag, (Animal) j);
                    if (((Animal) j).isPet()) {
                        petList.add(passengerLiveFlag);
                    }
                }
            }

            // Secondly, get pedestrian array in each scenario
            pedestrian = i.getPedestrians();
            for (Persona j : pedestrian) {
                // find shared persona attributes at the beginning
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(pedestrianLiveFlag);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(pedestrianLiveFlag);
                }
                if (j.getBodyType().toString().equals("AVERAGE")) {
                    averageBodyList.add(pedestrianLiveFlag);
                }
                if (j.getBodyType().toString().equals("ATHLETIC")) {
                    athleticBodyList.add(pedestrianLiveFlag);
                }
                if (j.getBodyType().toString().equals("OVERWEIGHT")) {
                    overweightBodyList.add(pedestrianLiveFlag);
                }

                if (j instanceof Human) {
                    humanList.add(pedestrianLiveFlag);
                    addHumanCategoryList(pedestrianLiveFlag, (Human) j);

//                    if (((Human) j).getProfession().toString().equals("DOCTOR")) {
//                        doctorList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("CEO")) {
//                        ceoList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("CRIMINAL")) {
//                        criminalList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("HOMELESS")) {
//                        homelessList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("UNEMPLOYED")) {
//                        unemployedList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("TEACHER")) {
//                        teacherList.add(pedestrianLiveFlag);
//                    }
//                    if (((Human) j).getProfession().toString().equals("CLEANER")) {
//                        cleanerList.add(pedestrianLiveFlag);
//                    }
                    if (((Human) j).isPregnant()) {
                        pregnantList.add(pedestrianLiveFlag);
                    }
                    // calculate alive average people age
                    if(! passengerLive){
                        sumAliveHumanAge+=j.getAge();
                    }

                }

                if (j instanceof Animal) {
                    animalList.add(pedestrianLiveFlag);
                    addAnimalCategoryList(pedestrianLiveFlag, (Animal) j);
                    if (((Animal) j).isPet()) {
                        petList.add(pedestrianLiveFlag);
                    }
                }
            }
        }
    }


    /**
     * This method runs the decide method and calculate the survival rate for different attributes.
     */
    public void calculateSurvivalRate() {
        for (Scenario i : scenarioList) {
            // run Ethical Engine's decide method
            String groupCanLive = EthicalEngine.decide(i).toString();

            if (groupCanLive.equals("PASSENGERS")) {
                Live(true);
            } else {
                Live(false);
            }
        }
        passengerSurviveRates = passengerSurviveCounts / (double) roundCounts;
        pedestrianSurviveRate = (double) (roundCounts - passengerSurviveCounts) / roundCounts;


        for (Integer i : greenList) {
            if (i == 1) {
                greenSurvive += 1;
            }
        }
        greenSurviveRate = greenSurvive / greenList.size();

        for (Integer i : redList) {
            if (i == 1) {
                redSurvive += 1;
            }
        }
        redSurviveRate = redSurvive / redList.size();

        for (Integer i : humanList) {
            if (i == 1) {
                humanSurvive += 1;
            }
        }
        humanSurviveRate = humanSurvive / humanList.size();
        // calculate average age for alive human
        aliveHumanAvgAge= sumAliveHumanAge / humanSurvive;


        for (Integer i : animalList) {
            if (i == 1) {
                animalSurvive += 1;
            }
        }
        animalSurviveRate = animalSurvive / animalList.size();

        for (Integer i : petList) {
            if (i == 1) {
                petSurvive += 1;
            }
        }
        petSurviveRate = petSurvive / petList.size();

        for (Integer i : maleList) {
            if (i == 1) {
                maleSurvive += 1;
            }
        }
        maleSurviveRate = maleSurvive / maleList.size();

        for (Integer i : femaleList) {
            if (i == 1) {
                femaleSurvive += 1;
            }
        }
        femaleSurviveRate = femaleSurvive / femaleList.size();

        for (Integer i : averageBodyList) {
            if (i == 1) {
                avgBodySurvive += 1;
            }
        }
        avgBodySurviveRate = avgBodySurvive / averageBodyList.size();

        for (Integer i : athleticBodyList) {
            if (i == 1) {
                athleticBodySurvive += 1;
            }
        }
        athleticSurviveRate = athleticBodySurvive / athleticBodyList.size();

        for (Integer i : overweightBodyList) {
            if (i == 1) {
                overweightBodySurvive += 1;
            }
        }
        overweightBodySurviveRate = overweightBodySurvive / overweightBodyList.size();

        for (Integer i : babyList) {
            if (i == 1) {
                babySurvive += 1;
            }
        }
        babySurviveRate = babySurvive / babyList.size();

        for (Integer i : childList) {
            if (i == 1) {
                childSurvive += 1;
            }
        }
        childSurviveRate = childSurvive / childList.size();

        for (Integer i : adultList) {
            if (i == 1) {
                adultSurvive += 1;
            }
        }
        adultSurviveRate = adultSurvive / adultList.size();

        for (Integer i : seniorList) {
            if (i == 1) {
                seniorSurvive += 1;
            }
        }
        seniorSurviveRate = seniorSurvive / seniorList.size();

        for (Integer i : pregnantList) {
            if (i == 1) {
                pregnantSurvive += 1;
            }
        }
        pregnantSurviveRate = pregnantSurvive / pregnantList.size();

        for (Integer i : doctorList) {
            if (i == 1) {
                doctorSurvive += 1;
            }
        }
        doctorSurviveRate = doctorSurvive / doctorList.size();

        for (Integer i : ceoList) {
            if (i == 1) {
                ceoSurvive += 1;
            }
        }
        ceoSurviveRate = ceoSurvive / ceoList.size();

        for (Integer i : homelessList) {
            if (i == 1) {
                homelessSurvive += 1;
            }
        }
        homelessSurviveRate = homelessSurvive / homelessList.size();

        for (Integer i : criminalList) {
            if (i == 1) {
                criminalSurvive += 1;
            }
        }
        criminalSurviveRate = criminalSurvive / criminalList.size();

        for (Integer i : unemployedList) {
            if (i == 1) {
                unemployedSurvive += 1;
            }
        }
        unemployedSurviveRate = unemployedSurvive / unemployedList.size();

        for (Integer i : teacherList) {
            if (i == 1) {
                teacherSurvive += 1;
            }
        }
        teacherSurviveRate = teacherSurvive / teacherList.size();

        for (Integer i : cleanerList) {
            if (i == 1) {
                cleanerSurvive += 1;
            }
        }
        cleanerSurviveRate = cleanerSurvive / cleanerList.size();

        for (Integer i : birdList) {
            if (i == 1) {
                birdSurvive += 1;
            }
        }
        birdSurviveRate = birdSurvive / birdList.size();

        for (Integer i : dogList) {
            if (i == 1) {
                dogSurvive += 1;
            }
        }
        dogSurviveRate = dogSurvive / dogList.size();

        for (Integer i : catList) {
            if (i == 1) {
                catSurvive += 1;
            }
        }
        catSurviveRate = catSurvive / catList.size();

        for (Integer i : turtleList) {
            if (i == 1) {
                turtleSurvive += 1;
            }
        }
        turtleSurviveRate = turtleSurvive / turtleList.size();

        for (Integer i : cowList) {
            if (i == 1) {
                cowSurvive += 1;
            }
        }
        cowSurviveRate = cowSurvive / cowList.size();

        for (Integer i : pigList) {
            if (i == 1) {
                pigSurvive += 1;
            }
        }
        pigSurviveRate = pigSurvive / pigList.size();

        for (Integer i : deerList) {
            if (i == 1) {
                deerSurvive += 1;
            }
        }
        deerSurviveRate = deerSurvive / deerList.size();

        for (Integer i : elephantList) {
            if (i == 1) {
                elephantSurvive += 1;
            }
        }
        elephantSurviveRate = elephantSurvive / elephantList.size();
    }


    // put key and value pairs into hashmap
    public void addIntoHashMap(HashMap<String,Double> map){
        map.put("passengers",passengerSurviveRates);
        map.put("pedestrians",pedestrianSurviveRate);
        map.put("animal",animalSurviveRate);
        map.put("human",humanSurviveRate);
        map.put("pet",petSurviveRate);
        map.put("overweight",overweightBodySurviveRate);
        map.put("average",avgBodySurviveRate);
        map.put("athletic",athleticSurviveRate);
        map.put("male",maleSurviveRate);
        map.put("female",femaleSurviveRate);
        map.put("pregnant",pregnantSurviveRate);
        map.put("green", greenSurviveRate);
        map.put("red", redSurviveRate);

        map.put("doctor",doctorSurviveRate);
        map.put("ceo",ceoSurviveRate);
        map.put("criminal",criminalSurviveRate);
        map.put("homeless",homelessSurviveRate);
        map.put("teacher",teacherSurviveRate);
        map.put("cleaner",cleanerSurviveRate);
        map.put("unemployed",unemployedSurviveRate);



    }




    public String toString() {
        String title = "======================================\n" +
                "# " + auditType + " Audit\n" +
                "======================================\n" +
                "- % SAVED AFTER " + roundCounts + " RUNS\n";

        String result = "";
        result += title;
        if (!scenarioList.isEmpty()) {
            for (Scenario i : scenarioList) {
                result += i;
            }
        } else {
            result = "no audit available";
        }
        return result + " male survival: " + maleSurviveRate + " femalsurvival: " + femaleSurviveRate +
                " baby " + babySurviveRate + " child " + childSurviveRate + " adult total " + adultList.size() +
                " adult survive: " + adultSurvive + " adult survive rate:" + adultSurviveRate + " senior " + seniorSurviveRate
                + "\n" + "DOCTOR " + doctorList.size() + "\n" + "human " + humanSurviveRate
                + "\n" + "animal " + animalSurviveRate + "\n" + "passenger " + passengerSurviveRates
                + "\n" + "pedes " + pedestrianSurviveRate + "\n" + "legal? " + greenSurviveRate +
                " alivehuman "+humanSurvive +"avg age "+aliveHumanAvgAge;
    }
}
