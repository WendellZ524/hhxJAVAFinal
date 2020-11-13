/**
 * COMP90041, Sem2, 2020: Final Project:
 * The class is for applying the decide() to different scenarios and decide the outcome
 * Then print to the command line or print to a file
 *
 * @throws Audit.DirectoryNotFound If the directory does not exists
 * @author: HAIXIANG HUANG
 * student id: 965205
 * username: HAIXIANGH
 * Github repository link:
 * https://github.com/COMP90041/final-project-moral-machines-haixiangh.git
 */

import ethicalengine.*;

import java.util.*;
import java.io.*;

public class Audit {
    static class DirectoryNotFound extends Exception {
        public DirectoryNotFound() {
            super("ERROR: could not print results. Target directory does not exist.");
        }
    }

    private String auditType = "Unspecified";

    // The arraylist that contains all scenarios
    private ArrayList<Scenario> scenarioArrayList = new ArrayList<Scenario>();

    /**
     * To delete everything in the scenarioArrayList
     */
    public void clearArraylist() {
        this.scenarioArrayList.clear();
    }

    /**
     * @return get the scenarioArrayList
     */
    public ArrayList<Scenario> getScenarioArrayList() {
        return scenarioArrayList;
    }

    // The arraylist contains the final data of survival rate for all groups
    private ArrayList<String> resultList = new ArrayList<String>();
    // the key is each category such as passenger, child, bird, etc. The value is survival rate
    private HashMap<String, Double> map = new HashMap<String, Double>();
    // result is the final formatted string that ready to be output
    private String result = "";

    // Counts how many scenarios have been assessed by decide() or user
    private int roundCounts = 0;
    // the age sum of survived human in all scenarios
    private double sumAliveHumanAge = 0;
    // the average age for survived human in all scenarios
    private double aliveHumanAvgAge = 0;

    // set up arraylists for corresponding attributes
    private ArrayList<Integer> maleList = new ArrayList<>();
    private ArrayList<Integer> femaleList = new ArrayList<>();
    private ArrayList<Integer> averageBodyList = new ArrayList<>();
    private ArrayList<Integer> athleticBodyList = new ArrayList<>();
    private ArrayList<Integer> overweightBodyList = new ArrayList<>();
    // arraylists specifically for human
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
    // arraylists specifically for animal and related scenarios
    private ArrayList<Integer> passengerAliveList = new ArrayList<>();
    private ArrayList<Integer> pedestrianAliveList = new ArrayList<>();
    private ArrayList<Integer> animalList = new ArrayList<>();
    private ArrayList<Integer> humanList = new ArrayList<>();
    private ArrayList<Integer> petList = new ArrayList<>();
    private ArrayList<Integer> greenList = new ArrayList<>();
    private ArrayList<Integer> redList = new ArrayList<>();
    private ArrayList<Integer> birdList = new ArrayList<>();
    private ArrayList<Integer> dogList = new ArrayList<>();
    private ArrayList<Integer> catList = new ArrayList<>();
    private ArrayList<Integer> turtleList = new ArrayList<>();
    private ArrayList<Integer> cowList = new ArrayList<>();
    private ArrayList<Integer> pigList = new ArrayList<>();
    private ArrayList<Integer> deerList = new ArrayList<>();
    private ArrayList<Integer> elephantList = new ArrayList<>();
    private ArrayList<Integer> youList = new ArrayList<>();

    // these variables store the number of each category survived
    private double youSurvive, passengerSurvive, pedestrianSurvive, greenSurvive, redSurvive, humanSurvive, animalSurvive, petSurvive, maleSurvive, femaleSurvive, avgBodySurvive, athleticBodySurvive, overweightBodySurvive,
            babySurvive, childSurvive, adultSurvive, seniorSurvive, doctorSurvive, ceoSurvive,
            criminalSurvive, homelessSurvive, unemployedSurvive, teacherSurvive, cleanerSurvive, pregnantSurvive,
            birdSurvive, dogSurvive, catSurvive, turtleSurvive, cowSurvive, pigSurvive, elephantSurvive, deerSurvive;

    // these variables is the survive rate in each category
    private double youSurviveRate, passengerSurviveRates, pedestrianSurviveRate, greenSurviveRate, redSurviveRate,
            humanSurviveRate, animalSurviveRate, petSurviveRate, maleSurviveRate, femaleSurviveRate, avgBodySurviveRate, athleticSurviveRate,
            overweightBodySurviveRate, babySurviveRate, childSurviveRate, adultSurviveRate, seniorSurviveRate,
            doctorSurviveRate, ceoSurviveRate, criminalSurviveRate, homelessSurviveRate, unemployedSurviveRate,
            teacherSurviveRate, cleanerSurviveRate, pregnantSurviveRate,
            birdSurviveRate, dogSurviveRate, catSurviveRate, turtleSurviveRate, cowSurviveRate, pigSurviveRate, elephantSurviveRate,
            deerSurviveRate;

    /**
     * The empty constructor
     */
    public Audit() {
    }

    /**
     * The constructor for Audit
     *
     * @param scenarioArr accept a Scenario array
     */
    public Audit(Scenario[] scenarioArr) {
        // transform the scenario array to the list then to the arraylist
        List<Scenario> tempList = Arrays.asList(scenarioArr.clone());
        this.scenarioArrayList = new ArrayList<Scenario>(tempList);
    }

    /**
     * To add one scenario to scenarioArrayList
     *
     * @param scenario a scenario instance
     */
    public void addScenario(Scenario scenario) {
        Scenario s = new Scenario();
        s = scenario;
        scenarioArrayList.add(s);
    }

    /**
     * @param auditType set the name of the audit type
     */
    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    /**
     * @return get the name of the audit type
     */
    public String getAuditType() {
        return auditType;
    }


    public void creatScenarios(int scenarioNum) {
        for (int i = 0; i < scenarioNum; i++) {
            ScenarioGenerator s = new ScenarioGenerator();
            scenarioArrayList.add(s.generate());
        }
    }

    /**
     * The private method is for adding numbers to attribute lists only for human
     *
     * @param liveFlag comes from Live() after deciding which group live
     * @param human    comes from the scenarioList that contains human instances
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
        if (human.isYou()) {
            youList.add(liveFlag);
        }
    }

    /**
     * The private method is for adding numbers to attribute lists only for animal
     *
     * @param liveFlag comes from Live() after deciding which group live
     * @param animal   comes from the scenarioList that contains animal instances
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
    public void live(boolean passengerLive) {
        int passengerLiveFlag, pedestrianLiveFlag;
        Persona[] passenger;
        Persona[] pedestrian;
        // passenger live implies pedestrians die, vice versa
        if (passengerLive) {
            passengerAliveList.add(1);
            pedestrianAliveList.add(0);
            passengerLiveFlag = 1;
            pedestrianLiveFlag = 0;
        } else {
            passengerAliveList.add(0);
            pedestrianAliveList.add(1);
            passengerLiveFlag = 0;
            pedestrianLiveFlag = 1;
        }

        // Firstly, get passenger array in each scenario
        for (Scenario i : scenarioArrayList) {
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
                    if (passengerLive) {
                        sumAliveHumanAge += j.getAge();
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
                    if (((Human) j).isPregnant()) {
                        pregnantList.add(pedestrianLiveFlag);
                    }
                    // calculate alive average people age
                    if (!passengerLive) {
                        sumAliveHumanAge += j.getAge();
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

        for (Scenario i : scenarioArrayList) {
            // in each scenario that has assessed, add counter by 1
            roundCounts += 1;
            // run Ethical Engine's decide method
            String groupCanLive = EthicalEngine.decide(i).toString();

            if (groupCanLive.equals("PASSENGERS")) {
                live(true);
            } else {
                live(false);
            }
        }

        for (Integer i : passengerAliveList) {
            if (i == 1) {
                passengerSurvive += 1;
            }
        }
        passengerSurviveRates = passengerSurvive / passengerAliveList.size();

        for (Integer i : pedestrianAliveList) {
            if (i == 1) {
                pedestrianSurvive += 1;
            }
        }
        pedestrianSurviveRate = pedestrianSurvive / pedestrianAliveList.size();

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
        aliveHumanAvgAge = sumAliveHumanAge / humanSurvive;


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

        for (Integer i : youList) {
            if (i == 1) {
                youSurvive += 1;
            }
        }
        youSurviveRate = youSurvive / youList.size();
    }

    /**
     * @param map put key and value pairs into hashmap
     */
    public void addIntoHashMap(HashMap<String, Double> map) {
        calculateSurvivalRate();
        map.put("average age", aliveHumanAvgAge);
        map.put("you", youSurviveRate);
        map.put("passengers", passengerSurviveRates);
        map.put("pedestrians", pedestrianSurviveRate);
        map.put("animal", animalSurviveRate);
        map.put("human", humanSurviveRate);
        map.put("pet", petSurviveRate);
        map.put("overweight", overweightBodySurviveRate);
        map.put("average", avgBodySurviveRate);
        map.put("athletic", athleticSurviveRate);
        map.put("male", maleSurviveRate);
        map.put("female", femaleSurviveRate);
        map.put("pregnant", pregnantSurviveRate);
        map.put("green", greenSurviveRate);
        map.put("red", redSurviveRate);

        map.put("doctor", doctorSurviveRate);
        map.put("ceo", ceoSurviveRate);
        map.put("criminal", criminalSurviveRate);
        map.put("homeless", homelessSurviveRate);
        map.put("teacher", teacherSurviveRate);
        map.put("cleaner", cleanerSurviveRate);
        map.put("unemployed", unemployedSurviveRate);
        map.put("baby", babySurviveRate);
        map.put("child", childSurviveRate);
        map.put("adult", adultSurviveRate);
        map.put("senior", seniorSurviveRate);

        map.put("bird", birdSurviveRate);
        map.put("dog", dogSurviveRate);
        map.put("cat", catSurviveRate);
        map.put("turtle", turtleSurviveRate);
        map.put("cow", cowSurviveRate);
        map.put("pig", pigSurviveRate);
        map.put("deer", deerSurviveRate);
        map.put("elephant", elephantSurviveRate);
    }

    /**
     * @param map the hashmap contains the calculated category and survive rate pair
     * @return sort the map in desc order
     */
    public List<Map.Entry<String, Double>> descHashMap(HashMap<String, Double> map) {
        // add all pairs of key-value
        addIntoHashMap(map);

        //transform map.entrySet() to list
        List<Map.Entry<String, Double>> list = new ArrayList<>();

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            // if the entry IS a double
            if (!entry.getValue().isNaN()) {
                list.add(entry);
            }
        }

        list.sort(new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                // compare() requires an int to return
                // to avoid downcast issue, multiply each double by 1000
                return (int) (o2.getValue() * 1000 - o1.getValue() * 1000);
            }
        });
        return list;
    }

    /**
     * @param resultList add the formatted output to result variable
     */
    public void saveEachResult(ArrayList<String> resultList) {
        // iterate through how many times the run() method have been run
        String result = "";
        double averageAge = 0;

        for (Map.Entry<String, Double> i : descHashMap(map)) {
            if (!i.getKey().equals("average age")) {
                result += i.getKey() + ": " + String.format("%.2f", i.getValue()) + "\n";
            } else {
                averageAge = i.getValue();
            }
        }

        String title = "======================================\n" +
                "# " + auditType + " Audit\n" +
                "======================================\n" +
                "- % SAVED AFTER " + roundCounts + " RUNS\n";

        result += "--" + "\n";
        result += "average age: " + String.format("%.2f", averageAge);
        resultList.add(title + result);
    }

    /**
     * @param runs create random scenarios with given numbers
     *             then get the result using decide()
     */
    public void run(int runs) {
        // create scenarios
        creatScenarios(runs);
        saveEachResult(resultList);

        // reset each attribute value
        youSurvive = 0;
        passengerSurvive = 0;
        pedestrianSurvive = 0;
        greenSurvive = 0;
        redSurvive = 0;
        humanSurvive = 0;
        animalSurvive = 0;
        petSurvive = 0;
        maleSurvive = 0;
        femaleSurvive = 0;
        avgBodySurvive = 0;
        athleticBodySurvive = 0;
        overweightBodySurvive = 0;
        babySurvive = 0;
        childSurvive = 0;
        adultSurvive = 0;
        seniorSurvive = 0;
        doctorSurvive = 0;
        ceoSurvive = 0;
        criminalSurvive = 0;
        homelessSurvive = 0;
        unemployedSurvive = 0;
        teacherSurvive = 0;
        cleanerSurvive = 0;
        pregnantSurvive = 0;
        birdSurvive = 0;
        dogSurvive = 0;
        catSurvive = 0;
        turtleSurvive = 0;
        cowSurvive = 0;
        pigSurvive = 0;
        elephantSurvive = 0;
        deerSurvive = 0;
    }

    /**
     * using the scenario from resultList (CSV imported)
     * then get the result using decide()
     */
    public void run() {
        saveEachResult(resultList);

        // reset each attribute value
        youSurvive = 0;
        passengerSurvive = 0;
        pedestrianSurvive = 0;
        greenSurvive = 0;
        redSurvive = 0;
        humanSurvive = 0;
        animalSurvive = 0;
        petSurvive = 0;
        maleSurvive = 0;
        femaleSurvive = 0;
        avgBodySurvive = 0;
        athleticBodySurvive = 0;
        overweightBodySurvive = 0;
        babySurvive = 0;
        childSurvive = 0;
        adultSurvive = 0;
        seniorSurvive = 0;
        doctorSurvive = 0;
        ceoSurvive = 0;
        criminalSurvive = 0;
        homelessSurvive = 0;
        unemployedSurvive = 0;
        teacherSurvive = 0;
        cleanerSurvive = 0;
        pregnantSurvive = 0;
        birdSurvive = 0;
        dogSurvive = 0;
        catSurvive = 0;
        turtleSurvive = 0;
        cowSurvive = 0;
        pigSurvive = 0;
        elephantSurvive = 0;
        deerSurvive = 0;
    }

    /**
     *
     * @return update the result variable from all data in resultList
     */
    public String toString() {
        for (String i : resultList) {
            result += i;
        }
        return result;

    }

    /**
     * print the result to the command line
     * The method has called toString(), if called toString() before,
     * the output will be doubled.
     */
    public void printStatistic() {
        toString();
        System.out.println(result);
    }


    /**
     * To print the results from toString() to the given file
     * The printToFile won't call toString(), to avoid empty file,
     * must either call toString() or printStatistic first, and only once.
     *
     * @param filepath the relative file path (including directory eg: "tests/testfile.txt")
     */
    public void printToFile(String filepath) {
        int indexOfEndDirectory = filepath.indexOf("/");
        try {
            if (indexOfEndDirectory == -1) {
                throw new DirectoryNotFound();
            } else {
                String directory = filepath.substring(0, indexOfEndDirectory);
                File dir = new File(directory);
                if (!dir.isDirectory()) {
                    throw new DirectoryNotFound();
                }
                File file = new File(filepath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(filepath, true);
                fileWriter.write(result);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException | DirectoryNotFound e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}

