import ethicalengine.Human;
import ethicalengine.Persona;
import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;


import java.util.ArrayList;

public class Audit {
    private String auditType = "Unspecified";
    private static ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
    private int roundCounts = 0;

    // 这些list后面要放到方法外面
    ArrayList<Integer> maleList = new ArrayList<>();
    ArrayList<Integer> femaleList = new ArrayList<>();
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

    public void passengerLive() {
        // Audit为每一个种类（Body，baby）开一个list，0生1死
        Persona[] passenger;
        Persona[] pedestrian;
        for (Scenario i : scenarioList) {
            // get passenger array in each scenario
            passenger = i.getPassengers();
            for (Persona j : passenger) {
                // find shared persona variables first
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(1);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(1);
                }

                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(1);
                    }
                }
            }

            // get pedestrian array in each scenario
            pedestrian = i.getPedestrians();
            for (Persona j : pedestrian) {
                // find shared persona variables first
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(0);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(0);
                }

                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(0);
                    }
                }
            }
        }
    }

    public void pedestrianLive() {
        // Audit为每一个种类（Body，baby）开一个list，0生1死
        System.out.println("乘客死了");
        Persona[] passenger;
        Persona[] pedestrian;

        // get passenger array in each scenario
        for (Scenario i : scenarioList) {
            passenger = i.getPassengers();
            for (Persona j : passenger) {
                // find shared persona variables first
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(0);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(0);
                }
                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(0);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(0);
                    }

                }
            }

            // get pedestrian array in each scenario
            pedestrian = i.getPedestrians();
            for (Persona j : pedestrian) {
                // find shared persona variables first
                // int 1 means live, int 0 means dead in the list
                if (j.getGender().toString().equals("MALE")) {
                    maleList.add(1);
                }
                if (j.getGender().toString().equals("FEMALE")) {
                    femaleList.add(1);
                }

                if (j instanceof Human) {
                    if (((Human) j).getAgeCategory().toString().equals("BABY")) {
                        babyList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
                        childList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
                        adultList.add(1);
                    }
                    if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
                        seniorList.add(1);
                    }
                }
            }
        }
    }

    public void createCharacteristicLists() {
        // Audit为每一个种类（Body，baby）开一个list，0生1死
//        Persona[] passenger;
//        Persona[] pedestrian;
        for (Scenario i : scenarioList) {
            // run Ethical Engine's decide method
            String groupCanLive = EthicalEngine.decide(i).toString();

            if (groupCanLive.equals("PASSENGERS")) {
                passengerLive();

//                // get passenger array in each scenario
//                passenger = i.getPassengers();
//                for (Persona j : passenger) {
//                    // find shared persona variables first
//                    // int 1 means live, int 0 means dead in the list
//                    if (j.getGender().toString().equals("MALE")) {
//                        maleList.add(1);
//                    }
//                    if (j.getGender().toString().equals("FEMALE")) {
//                        femaleList.add(1);
//                    }
//
//                    if (j instanceof Human) {
//                        if (((Human) j).getAgeCategory().toString().equals("BABY")) {
//                            babyList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
//                            childList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
//                            adultList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
//                            seniorList.add(1);
//                        }
//                    }
//                }
//
//                // get pedestrian array in each scenario
//                pedestrian = i.getPedestrians();
//                for (Persona j : pedestrian) {
//                    // find shared persona variables first
//                    // int 1 means live, int 0 means dead in the list
//                    if (j.getGender().toString().equals("MALE")) {
//                        maleList.add(0);
//                    }
//                    if (j.getGender().toString().equals("FEMALE")) {
//                        femaleList.add(0);
//                    }
//
//                    if (j instanceof Human) {
//                        if (((Human) j).getAgeCategory().toString().equals("BABY")) {
//                            babyList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
//                            childList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
//                            adultList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
//                            seniorList.add(0);
//                        }
//                    }
//                }


            } else {
                pedestrianLive();
//                // get passenger array in each scenario
//                System.out.println("乘客死了");
//                passenger = i.getPassengers();
//                for (Persona j : passenger) {
//                    // find shared persona variables first
//                    // int 1 means live, int 0 means dead in the list
//                    if (j.getGender().toString().equals("MALE")) {
//                        maleList.add(0);
//                    }
//                    if (j.getGender().toString().equals("FEMALE")) {
//                        femaleList.add(0);
//                    }
//                    if (j instanceof Human) {
//                        if (((Human) j).getAgeCategory().toString().equals("BABY")) {
//                            babyList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
//                            childList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
//                            adultList.add(0);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
//                            seniorList.add(0);
//                        }
//
//                    }
//                }
//
//                // get pedestrian array in each scenario
//                pedestrian = i.getPedestrians();
//                for (Persona j : pedestrian) {
//                    // find shared persona variables first
//                    // int 1 means live, int 0 means dead in the list
//                    if (j.getGender().toString().equals("MALE")) {
//                        maleList.add(1);
//                    }
//                    if (j.getGender().toString().equals("FEMALE")) {
//                        femaleList.add(1);
//                    }
//
//                    if (j instanceof Human) {
//                        if (((Human) j).getAgeCategory().toString().equals("BABY")) {
//                            babyList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("CHILD")) {
//                            childList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("ADULT")) {
//                            adultList.add(1);
//                        }
//                        if (((Human) j).getAgeCategory().toString().equals("SENIOR")) {
//                            seniorList.add(1);
//                        }
//                    }
//                }

            }
        }

        // 考虑分子为0的情况
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
        return result+"male survival: "+maleSurviveRate+" femalsurvival: "+femaleSurviveRate+
                "baby "+ babySurviveRate+"child "+childSurviveRate+"adult total "+adultList.size()+
                " adult survive:"+adultSurvive+" adult survive rate:"+adultSurviveRate+"senior"+seniorSurviveRate;
    }
}
