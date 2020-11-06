import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import ethicalengine.*;

import java.io.*;
import java.util.ArrayList;


/**
 * COMP90041, Sem2, 2020: Final Project: A skeleton code for you to update
 *
 * @author: HAIXIANG HUANG
 */
public class EthicalEngine {
    static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException() {
            super();
        }

        public InvalidDataFormatException(String s) {
            super(s);
        }
    }

    public static int getLineNumber() {
        int i = 1;
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int lineNumber = stacks[i].getLineNumber();
        return lineNumber;
    }

    private static ArrayList<String[]> importedCSVData = new ArrayList<String[]>();

    public enum Decision {PASSENGERS, PEDESTRIANS}

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
            else if (i instanceof Animal) {
                if (((Animal) i).isPet()) {
                    pedestrianWeight += 0.05;
                }
                if (((Animal) i).getSpecies().equals("bird")) {
                    pedestrianWeight -= 0.05;
                }
            }

        }

        if (passengerWeight > pedestrianWeight) {
            return Decision.PASSENGERS;
        } else {
            return Decision.PEDESTRIANS;
        }
    }

    public static void importConfig(String filepath) {
        File file = new File(filepath);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("ERROR: could not find config file.");
            } else {
                // read data from CSV
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                // the first row is information (class,gender...)
                reader.readLine();

                String line = null;

                //add all CSV data to the line (one line at a time)
                while ((line = reader.readLine()) != null) {
                    String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                    importedCSVData.add(item);
                    String last = item[item.length - 1];//这就是你要的数据了
                    //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                }
                String[] endMarkArray = new String[1];
                endMarkArray[0] = "End Mark";
                importedCSVData.add(endMarkArray);

//                //format the imported data
//                String[] eachPersonaInstance=new String[11];
//                for(String[] i:importedCSVData){
//                    for(String j:i){
//                        if(j.startsWith("scenario")){
//
//                        }
//                        System.out.println(j);
//                    }
//                }


            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Scenario> createCSVScenario() {

        ArrayList<Scenario> ScenarioList = new ArrayList<Scenario>();
        ArrayList<Persona> passenger = new ArrayList<Persona>();
        ArrayList<Persona> pedestrian = new ArrayList<Persona>();

        int ScenarioID = -1;

        for (String[] line : importedCSVData) {  // For each line in CSV
            try {
                // if in rows that not have 10 values (except for scenario and End Mark)
                // throw an Exception and continue
                if (line.length != 10 && !line[0].startsWith("scenario") && !line[0].equals("End Mark")) {
                    throw new InvalidDataFormatException("WARNING: invalid characteristic in config file in" +
                            "line < " + getLineNumber() + " >");
                }
            } catch (InvalidDataFormatException e) {
                System.out.println(e.getMessage());
            }

            if (line[0].startsWith("scenario") || line[0].equals("End Mark")) {
                if (ScenarioID == -1) {
                    ScenarioList.add(new Scenario());
                    ScenarioID++;
                    ScenarioList.get(ScenarioID).setLegalCrossing(line[0].substring(9).equals("green"));

                } else {
                    Persona[] passengerArr = new Persona[passenger.size()];
                    Persona[] pedestrianArr = new Persona[pedestrian.size()];

                    for (int i = 0; i < passengerArr.length; i++) {
                        passengerArr[i] = passenger.get(i);
                    }

                    for (int i = 0; i < pedestrianArr.length; i++) {
                        pedestrianArr[i] = pedestrian.get(i);
                    }
                    ScenarioList.get(ScenarioID).setPassengers(passengerArr);

                    ScenarioList.get(ScenarioID).setPedestrians(pedestrianArr);
                    passenger.clear();
                    pedestrian.clear();

                    if (!line[0].equals("End Mark")) {
                        ScenarioList.add(new Scenario());
                        ScenarioID++;
                        ScenarioList.get(ScenarioID).setLegalCrossing(line[0].substring(9).equals("green"));
                    }

                }

            } else {
                if (line[0].equals("human")) { // create an human instance
                    Persona.Gender gender = Persona.Gender.valueOf(line[1].toUpperCase());
                    int age = Integer.parseInt(line[2]);

                    Persona.BodyType bodyType = Persona.BodyType.UNSPECIFIED;
                    if (!line[3].equals("")) {
                        bodyType = Persona.BodyType.valueOf(line[3].toUpperCase());
                    }

                    Persona.Profession profession = Persona.Profession.NONE;
                    if (!line[4].equals("")) {
                        profession = Persona.Profession.valueOf(line[4].toUpperCase());
                    }
                    boolean isPregnant = line[5].equals("TRUE");
                    Human human = new Human(age, profession, gender, bodyType, isPregnant);
                    human.setAsYou(line[6].equals("TRUE"));
                    if (line[9].equals("passenger")) {
                        passenger.add(human);
                    } else if (line[9].equals("pedestrian")) {
                        pedestrian.add(human);
                    }

                } else if (line[0].equals("animal")) {
                    Persona.Gender gender = Persona.Gender.valueOf(line[1].toUpperCase());
                    int age = Integer.parseInt(line[2]);

                    Persona.BodyType bodyType = Persona.BodyType.UNSPECIFIED;
                    if (!line[3].equals("")) {
                        bodyType = Persona.BodyType.valueOf(line[3].toUpperCase());
                    }

                    Animal animal = new Animal(line[7]);
                    animal.setPet(line[8].equals("TRUE"));
                    animal.setAge(age);
                    animal.setGender(gender);
                    animal.setBodyType(bodyType);
                    if (line[9].equals("passenger")) {
                        passenger.add(animal);
                    } else if (line[9].equals("pedestrian")) {
                        pedestrian.add(animal);
                    }
                }
            }
        }
        return ScenarioList;
    }


    public static void main(String[] args) {
        importConfig("C:\\Users\\ae952\\Desktop\\Github Java\\hhxJAVAFinal\\tests\\config.csv");
        EthicalEngine e1 = new EthicalEngine();
        for (Scenario i : e1.createCSVScenario()) {
            System.out.println(i);
        }
    }
}




