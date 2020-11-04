import ethicalengine.Human;
import ethicalengine.Persona;
import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;


import java.util.ArrayList;

public class Audit {
    private String auditType = "Unspecified";
    private static ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
    private int roundCounts = 0;

    // set up lists for corresponding attributes
    ArrayList<Integer> maleList = new ArrayList<>();
    ArrayList<Integer> femaleList = new ArrayList<>();
    ArrayList<Integer> averageBodyList = new ArrayList<>();
    ArrayList<Integer> athleticBodyList = new ArrayList<>();
    ArrayList<Integer> overweightBodyList = new ArrayList<>();



    ArrayList<Integer> babyList = new ArrayList<>();
    ArrayList<Integer> childList = new ArrayList<>();
    ArrayList<Integer> adultList = new ArrayList<>();
    ArrayList<Integer> seniorList = new ArrayList<>();

    double maleSurvive, femaleSurvive, babySurvive, childSurvive, adultSurvive, seniorSurvive;

    double  maleSurviveRate, femaleSurviveRate, babySurviveRate, childSurviveRate, adultSurviveRate,
            seniorSurviveRate;


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
     * This method set the flag (0 or 1), which correspondingly represents death or live in each
     * attributes lists
     * @param passengerLive generated from decide method (EthicalEngine) that represents if
     *                      the passenger is alive.
     */
    public void Live(boolean passengerLive) {
        int passengerLiveFlag, pedestrianLiveFlag;
        Persona[] passenger;
        Persona[] pedestrian;

        // passenger live implies pedestrians die, vice versa
        if(passengerLive){
            passengerLiveFlag=1;
            pedestrianLiveFlag=0;
        }
        else {
            passengerLiveFlag=0;
            pedestrianLiveFlag=1;
        }

        // Firstly, get passenger array in each scenario
        for (Scenario i : scenarioList) {
            passenger = i.getPassengers();
            for (Persona j : passenger) {
                // find shared persona attributes at the beginning
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(passengerLiveFlag);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(passengerLiveFlag);
                }
                if(j.getBodyType().toString().equals("AVERAGE")){
                    averageBodyList.add(passengerLiveFlag);
                }
                if(j.getBodyType().toString().equals("ATHLETIC")){
                    athleticBodyList.add(passengerLiveFlag);
                }
                if(j.getBodyType().toString().equals("OVERWEIGHT")){
                    overweightBodyList.add(passengerLiveFlag);
                }

                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(passengerLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(passengerLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(passengerLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(passengerLiveFlag);
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
                if(j.getBodyType().toString().equals("AVERAGE")){
                    averageBodyList.add(pedestrianLiveFlag);
                }
                if(j.getBodyType().toString().equals("ATHLETIC")){
                    athleticBodyList.add(pedestrianLiveFlag);
                }
                if(j.getBodyType().toString().equals("OVERWEIGHT")){
                    overweightBodyList.add(pedestrianLiveFlag);
                }

                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(pedestrianLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(pedestrianLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(pedestrianLiveFlag);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(pedestrianLiveFlag);
                    }
                }
            }
        }
    }



    /**
     * This method runs the decide method and calculate the survival rate for different attributes.
     */
    public void createCharacteristicLists() {
        for (Scenario i : scenarioList) {
            // run Ethical Engine's decide method
            String groupCanLive = EthicalEngine.decide(i).toString();

            if (groupCanLive.equals("PASSENGERS")) {
                Live(true);

            } else {
                Live(false);

            }
        }


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
        return result+" male survival: "+maleSurviveRate+" femalsurvival: "+femaleSurviveRate+
                " baby "+ babySurviveRate+" child "+childSurviveRate+" adult total "+adultList.size()+
                " adult survive: "+adultSurvive+" adult survive rate:"+adultSurviveRate+" senior "+seniorSurviveRate;
    }
}
