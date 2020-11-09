import ethicalengine.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * COMP90041, Sem2, 2020: Final Project: A skeleton code for you to update
 *
 * @author: HAIXIANG HUANG
 */
public class EthicalEngine {

    private static Scanner sc = new Scanner(System.in);
    // the arraylist contains all CSV based scenarios, and should be the input for Audit constructor
    private static ArrayList<Scenario> scenarioFromCSV;

    // the arraylist contains all random generated scenarios
    private static ArrayList<Scenario> scenarioFromRandom;

    static class InvalidDataFormatException extends Exception {
        public InvalidDataFormatException() {
            super();
        }

        public InvalidDataFormatException(String s) {
            super(s);
        }
    }

    static class InvalidCharacteristicException extends Exception {
        public InvalidCharacteristicException() {
            super();
        }

        public InvalidCharacteristicException(String s) {
            super(s);
        }
    }

    static class NumberFormatException extends Exception {
        public NumberFormatException() {
            super();
        }

        public NumberFormatException(String s) {
            super(s);
        }
    }


    /**
     * To find if there's digits in a string
     *
     * @param str Input a String
     * @return boolean if the string has digits
     */
    public static boolean isDigit(String str) {
        return str.matches("[0-9]{1,}");
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


        Double passengerWeight = 0.5;
        Double pedestrianWeight = 0.5;
        // for interactive mode setting that allow passenger live
        if (scenario.getPassengerLiveWeight() > 0) {
            passengerWeight += 100;
        }
        // for interactive mode setting that allow pedestrian live
        if (scenario.getPassengerLiveWeight() < 0) {
            pedestrianWeight += 100;
        }

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


    /**
     * To import the data from CSV file to the arraylist called importedCSVData
     *
     * @param filepath the path of file
     */
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
                    String[] item = line.split(",");
                    importedCSVData.add(item);
                }
                reader.close();
                String[] endMarkArray = new String[1];
                endMarkArray[0] = "End Mark";
                importedCSVData.add(endMarkArray);

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To create Scenarios based on the imported CSV data (importedCSVData)
     * An arraylist called scenarioFromCSV is imported and filled with scenarios
     */
    public static void createCSVScenario() {

        ArrayList<Scenario> ScenarioList = new ArrayList<Scenario>();
        ArrayList<Persona> passenger = new ArrayList<Persona>();
        ArrayList<Persona> pedestrian = new ArrayList<Persona>();

        int ScenarioID = -1;
        int lineCounter = 1;   // it's not 0 because the title row has been removed from CSV

        for (String[] line : importedCSVData) {  // For each line in CSV
            lineCounter++;
            try {
                // if in rows that not have 10 values (except for scenario and End Mark)
                // throw an Exception and continue
                if (line.length != 10 && !line[0].startsWith("scenario") && !line[0].equals("End Mark")) {
                    throw new InvalidDataFormatException("WARNING: invalid data format in config file in" +
                            "line < " + lineCounter + " >");
                }
            } catch (InvalidDataFormatException e) {
                System.out.println(e.getMessage());
            }

            //End mark is located in the last line
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

                    //End mark is located in the last line
                    if (!line[0].equals("End Mark")) {
                        ScenarioList.add(new Scenario());
                        ScenarioID++;
                        ScenarioList.get(ScenarioID).setLegalCrossing(line[0].substring(9).equals("green"));
                    }
                }
            } else {
                // add attributes to genderList for further judgement
                ArrayList<String> genderList = new ArrayList<>();
                for (Persona.Gender i : Persona.Gender.values()) {
                    genderList.add(i.toString());
                }

                // add attributes to bodyList for further judgement
                ArrayList<String> bodyList = new ArrayList<>();
                for (Persona.BodyType i : Persona.BodyType.values()) {
                    bodyList.add(i.toString());
                }

                // add attributes to professionLists for further judgement
                ArrayList<String> professionList = new ArrayList<>();
                for (Persona.Profession i : Persona.Profession.values()) {
                    professionList.add(i.toString());
                }


                if (line[0].equals("human")) { // create a human instance

                    // adding gender
                    Persona.Gender gender = null;
                    try {
                        if (!line[1].equals("") || !isDigit(line[1])) {
                            if (genderList.contains(line[1].toUpperCase())) {
                                gender = Persona.Gender.valueOf(line[1].toUpperCase());
                            } else {
                                throw new InvalidCharacteristicException(
                                        "WARNING: invalid characteristic in config file in" +
                                                "line < " + lineCounter + " >");
                            }
                        } else { // if the gender cell is empty
                            throw new NumberFormatException(
                                    "WARNING: invalid number format in config file in" +
                                            "line < " + lineCounter + " >");
                        }
                    } catch (InvalidCharacteristicException | NumberFormatException e) {
                        // reset to the default value
                        System.out.println(e.getMessage());
                        gender = Persona.Gender.UNKNOWN;
                    }

                    // adding age
                    int age = Integer.parseInt(line[2]);

                    // adding bodyType
                    Persona.BodyType bodyType = null;
                    try {
                        if (!line[3].equals("") || !isDigit(line[3])) {
                            if (bodyList.contains(line[3].toUpperCase())) {
                                bodyType = Persona.BodyType.valueOf(line[3].toUpperCase());
                            } else {
                                throw new InvalidCharacteristicException(
                                        "WARNING: invalid characteristic in config file in" +
                                                "line < " + lineCounter + " >");
                            }
                        } else { // if the gender cell is empty
                            throw new NumberFormatException(
                                    "WARNING: invalid number format in config file in" +
                                            "line < " + lineCounter + " >");
                        }
                    } catch (InvalidCharacteristicException | NumberFormatException e) {
                        bodyType = Persona.BodyType.UNSPECIFIED;
                        System.out.println(e.getMessage());
                    }

                    // adding profession
                    Persona.Profession profession = null;
                    try {
                        if (!line[4].equals("") || !isDigit(line[4])) {
                            // check if the value is in the profession array
                            if (professionList.contains(line[4].toUpperCase())) {
                                profession = Persona.Profession.valueOf(line[4].toUpperCase());
                            } else {
                                throw new InvalidCharacteristicException(
                                        "WARNING: invalid characteristic in config file in" +
                                                "line < " + lineCounter + " >");
                            }
                        } else { // if the gender cell is empty
                            throw new NumberFormatException(
                                    "WARNING: invalid number format in config file in" +
                                            "line < " + lineCounter + " >");
                        }

                    } catch (InvalidCharacteristicException | NumberFormatException e) {
                        profession = Persona.Profession.NONE;
                        System.out.println(e.getMessage());
                    }


                    boolean isPregnant = line[5].toUpperCase().equals("TRUE");
                    // create human instance
                    Human human = new Human(age, profession, gender, bodyType, isPregnant);

                    human.setAsYou(line[6].toUpperCase().equals("TRUE"));
                    if (line[9].equals("passenger")) {
                        passenger.add(human);
                    } else if (line[9].equals("pedestrian")) {
                        pedestrian.add(human);
                    }

                } else if (line[0].equals("animal")) { // create an animal instance

                    // adding gender
                    Persona.Gender gender = null;
                    try {
                        if (!line[1].equals("")) {
                            if (genderList.contains(line[1].toUpperCase()) || !isDigit(line[1])) {
                                gender = Persona.Gender.valueOf(line[1].toUpperCase());
                            } else {
                                throw new InvalidCharacteristicException(
                                        "WARNING: invalid characteristic in config file in" +
                                                "line < " + lineCounter + " >");
                            }
                        } else { // if the gender cell is empty
                            throw new NumberFormatException(
                                    "WARNING: invalid number format in config file in" +
                                            "line < " + lineCounter + " >");
                        }
                    } catch (InvalidCharacteristicException | NumberFormatException e) {
                        // reset to the default value
                        System.out.println(e.getMessage());
                        gender = Persona.Gender.UNKNOWN;
                    }

                    int age = Integer.parseInt(line[2]);

                    // adding bodyType
                    Persona.BodyType bodyType = null;
                    try {
                        if (!line[3].equals("")) {
                            if (bodyList.contains(line[3].toUpperCase()) || !isDigit(line[3])) {
                                bodyType = Persona.BodyType.valueOf(line[3].toUpperCase());
                            } else {
                                throw new InvalidCharacteristicException(
                                        "WARNING: invalid characteristic in config file in" +
                                                "line < " + lineCounter + " >");
                            }
                        } else {
                            throw new NumberFormatException(
                                    "WARNING: invalid number format in config file in" +
                                            "line < " + lineCounter + " >");
                        }
                    } catch (InvalidCharacteristicException | NumberFormatException e) {
                        bodyType = Persona.BodyType.UNSPECIFIED;
                        System.out.println(e.getMessage());
                    }

                    Animal animal = new Animal(line[7]);
                    animal.setPet(line[8].toUpperCase().equals("TRUE"));

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
        scenarioFromCSV = ScenarioList;

    }

    /**
     * To transform a scenario arraylist to array
     *
     * @param scenarioArrayList an scenarioList of type scenario
     * @return a scenario array
     */
    public static Scenario[] toScenarioArray(ArrayList<Scenario> scenarioArrayList) {
        Scenario[] scenarioArr = new Scenario[scenarioArrayList.size()];
        for (int i = 0; i < scenarioArr.length; i++) {
            scenarioArr[i] = scenarioArrayList.get(i);
        }
        return scenarioArr;
    }

    public void printWelcome() {
        String header = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./welcome.ascii"));

            while ((header = reader.readLine()) != null) {
                System.out.println(header);
            }
//            while (reader.readLine() != null) {
//                header += reader.readLine();
//                header += "\n";
//            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String printHelp() {
        String help = "EthicalEngine - COMP90041 - Final Project\n" +
                "Usage: java EthicalEngine [arguments]\n" +
                "Arguments:\n" +
                "-c or --config Optional: path to config file\n" +
                "-h or --help Print Help (this message) and exit\n" +
                "-r or --results Optional: path to results log file\n" +
                "-i or --interactive Optional: launches interactive mode\n";
        return help;
    }


    public static void main(String[] args) {
        EthicalEngine ethicalEngine = new EthicalEngine();
        ethicalEngine.printWelcome(); // print the welcome header
        System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
        String consentANS;
        while (true) {
            consentANS = sc.nextLine();
            if (!consentANS.equals("yes") && !consentANS.equals("no")) {
                System.out.println("Invalid response. " +
                        "Do you consent to have your decisions saved to a file? (yes/no)");
            } else { // if answer is yes, then save the statistic to user.log in interactive-part code
                break;
            }
        }


        String inputFilePath = ""; // the path of CSV file that should be imported
        String outputFilePath = "./results.log"; // the default path of statistic result
        String str = "java EthicalEngine -h";
        // Using split() to split the user input
        String[] strArr = str.split(" ");

        // transform the array to list to facilitate searching flag
        List<String> strList = Arrays.asList(strArr);
        for (String i : strArr) {
            System.out.println(i);
        }

        int configIsExist = -1;
        int interactiveIsExist = -1;
        int resultIsExist = -1;

        // if has help option
        if (strList.contains("-h") || strList.contains("--help")) {
            System.out.println(printHelp());
            System.exit(0);
        } else {

            try {
                // if has config option
                if (strList.contains("-c") || strList.contains("--config")) {
                    configIsExist = strList.indexOf("-c"); // see if -c exists
                    if (configIsExist == -1) { // if -c not exists
                        configIsExist = strList.indexOf("--config"); // see if --config exists
                        // if --config exists, update configIsExist
                    }
                    // if the next index is a path containing csv
                    if (strList.get(configIsExist + 1).contains(".csv")) {
                        inputFilePath = strList.get(configIsExist + 1);
                    } else { // the format is wrong
                        System.out.println(printHelp());
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The format is wrong");
                System.out.println(printHelp()); // format is wrong
            }

            // if has interactive option
            if (strList.contains("-i") || strList.contains("--interactive")) {
                interactiveIsExist = strList.indexOf("-i");
                if (interactiveIsExist == -1) { // if -i not exists
                    interactiveIsExist = strList.indexOf("--config"); // see if --interactive exists
                    // if --interactive exist, update interactiveIsExist
                }
            }

            try {
                // if has results option
                if (strList.contains("-r") || strList.contains("--results")) {
                    resultIsExist = strList.indexOf("-r"); // see if -r exists
                    if (resultIsExist == -1) { // if -r not exists
                        resultIsExist = strList.indexOf("--results"); // see if --results exists
                        // if --results exists, update resultIsExist
                    }
                    // if -r was provided, there must be a path followed
                    // if -r option is not provided, save to default dir
                    outputFilePath = strList.get(resultIsExist + 1);

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The format is wrong");
                System.out.println(printHelp()); // format is wrong
            }
        }


        // if no config or interactive option
        if (configIsExist == -1 && interactiveIsExist == -1) {
            Audit a1 = new Audit();
            // generate 100 random scenarios
            a1.run(100);
            // write the data from local variable to log file (current folder)
            // printStatistic() will call toString()
            a1.printStatistic(); // print to command line
            // to avoid empty file, either call toString() or printStatistic() before printToFile()
            a1.printToFile(outputFilePath); // print to file
        }

        // if there's config without interactive
        if (configIsExist != -1 && interactiveIsExist == -1) {
            importConfig(inputFilePath); // import the data from CSV
            createCSVScenario(); // create Scenarios using the data
            // scenarioFromCSV is the arraylist that contains scenarios based on CSV data

            // transform the arraylist to Scenario[] array for audit constructor
            Scenario[] scenarioArr = scenarioFromCSV.toArray(new Scenario[0]);
            Audit a1 = new Audit(scenarioArr);
            a1.run();
            a1.printStatistic();
            // to avoid empty file, either call toString() or printStatistic() before printToFile()
            a1.printToFile(outputFilePath);
        }


        // interactive mode without config
        if (configIsExist == -1 && interactiveIsExist != -1) {
            // random create 100 scenarios
            Audit a = new Audit();
            a.creatScenarios(100);
            // shallow clone of the scenarioArrayList in Audit
            scenarioFromRandom = (ArrayList<Scenario>) a.getScenarioArrayList().clone();
            a.clearArraylist(); // clear scenarioArrayList in Audit for further calculation

            /**
             * This block is the core of interactive decision making
             */
            {   // shows how many scenarios left in the scenario arraylist
                int scenariosLeft = scenarioFromRandom.size();
                int scenariosCounter = 0;

                String continueAns = "yes";
                String quitAns = "a"; // can't preset to "" (because it is "enter")
                String saveGroup = "";
                outerLoop:
                do {
                    if (continueAns.equals("yes")) {
                        if (scenariosLeft >= 3) {
                            for (int i = 0; i < 3; i++) {
                                // print each scenario
                                System.out.println(scenarioFromRandom.get(scenariosCounter));
                                while (true) { // decide which group to live
                                    System.out.println("Who should be saved? " +
                                            "(passenger(s) [1] or pedestrian(s) [2])");
                                    saveGroup = sc.nextLine();
                                    if (saveGroup.equals("1") || saveGroup.equals("passenger") ||
                                            saveGroup.equals("passengers")) {
                                        // set to positive int to allow passenger live
                                        scenarioFromRandom.get(scenariosCounter).setPassengerLiveWeight(1);
                                        break;
                                    } else if (saveGroup.equals("2") || saveGroup.equals("pedestrian") ||
                                            saveGroup.equals("pedestrians")) {
                                        // set to negative int to allow pedestrian live
                                        scenarioFromRandom.get(scenariosCounter).setPassengerLiveWeight(-1);
                                        break;
                                    }
                                }
                                // add modified scenario to the audit instance
                                a.addScenario(scenarioFromRandom.get(scenariosCounter));
                                scenariosLeft -= 1;
                                scenariosCounter += 1;
                            }
                        } else { // number of scenarios in the list is less than 3
                            for (int i = 0; i < scenariosLeft; i++) {
                                System.out.println(scenarioFromRandom.get(scenariosCounter));
                                a.addScenario(scenarioFromRandom.get(scenariosCounter));
                                scenariosLeft -= 1;
                                scenariosCounter += 1;
                            }
                        }
                    } else {
                        if (quitInteractive(consentANS, a, continueAns)) break outerLoop;
                    }

                    if (scenariosLeft == 0) {
                        a.setAuditType("User");
                        a.run();
                        a.printStatistic();
                        if (consentANS.equals("yes")) { // save the user log if permitted
                            a.printToFile("./user.log");
                        }
                        while (true) {
                            System.out.println("That’s all. Press Enter to quit.");
                            quitAns = sc.nextLine();
                            if (quitAns.equals("")) { // enter
                                break outerLoop;
                            }
                        }

                    }
                    System.out.println("Would you like to continue? (yes/no)");
                    continueAns = sc.nextLine();
                } while (true);
            }
        }


        // interactive mode with config
        if (configIsExist != -1 && interactiveIsExist != -1) {
            // using scenario from imported CSV
            importConfig(inputFilePath); // import the data from CSV
            createCSVScenario(); // create Scenarios using the data
            // scenarioFromCSV is the arraylist that contains scenarios based on CSV data

            /**
             * This block is the core of interactive decision making
             */
            {   // shows how many scenarios left in the scenario arraylist
                int scenariosLeft = scenarioFromCSV.size();
                int scenariosCounter = 0;
                Audit a = new Audit();
                String continueAns = "yes";
                String quitAns = "a"; // can't preset to "" (because it is "enter")
                String saveGroup = "";
                outerLoop:
                do {
                    if (continueAns.equals("yes")) {
                        if (scenariosLeft >= 3) {
                            for (int i = 0; i < 3; i++) {
                                // print each scenario
                                System.out.println(scenarioFromCSV.get(scenariosCounter));
                                while (true) { // decide which group to live
                                    System.out.println("Who should be saved? " +
                                            "(passenger(s) [1] or pedestrian(s) [2])");
                                    saveGroup = sc.nextLine();
                                    if (saveGroup.equals("1") || saveGroup.equals("passenger") ||
                                            saveGroup.equals("passengers")) {
                                        // set to positive int to allow passenger live
                                        scenarioFromCSV.get(scenariosCounter).setPassengerLiveWeight(1);
                                        break;
                                    } else if (saveGroup.equals("2") || saveGroup.equals("pedestrian") ||
                                            saveGroup.equals("pedestrians")) {
                                        // set to negative int to allow pedestrian live
                                        scenarioFromCSV.get(scenariosCounter).setPassengerLiveWeight(-1);
                                        break;
                                    }
                                }
                                // add modified scenario to the audit instance
                                a.addScenario(scenarioFromCSV.get(scenariosCounter));
                                scenariosLeft -= 1;
                                scenariosCounter += 1;
                            }
                        } else { // number of scenarios in the list is less than 3
                            for (int i = 0; i <= scenariosLeft; i++) {
                                System.out.println(scenarioFromCSV.get(scenariosCounter));
                                while (true) { // decide which group to live
                                    System.out.println("Who should be saved? " +
                                            "(passenger(s) [1] or pedestrian(s) [2])");
                                    saveGroup = sc.nextLine();
                                    if (saveGroup.equals("1") || saveGroup.equals("passenger") ||
                                            saveGroup.equals("passengers")) {
                                        // set to positive int to allow passenger live
                                        scenarioFromCSV.get(scenariosCounter).setPassengerLiveWeight(1);
                                        break;
                                    } else if (saveGroup.equals("2") || saveGroup.equals("pedestrian") ||
                                            saveGroup.equals("pedestrians")) {
                                        // set to negative int to allow pedestrian live
                                        scenarioFromCSV.get(scenariosCounter).setPassengerLiveWeight(-1);
                                        break;
                                    }
                                }
                                a.addScenario(scenarioFromCSV.get(scenariosCounter));
                                scenariosLeft -= 1;
                                scenariosCounter += 1;
                            }
                        }
                    } else if (quitInteractive(consentANS, a, continueAns)) break;
                    if (scenariosLeft == 0) {
                        a.setAuditType("User");
                        a.run();
                        a.printStatistic();
                        if (consentANS.equals("yes")) { // save the user log if permitted
                            a.printToFile("./user.log");
                        }
                        while (true) {
                            System.out.println("That’s all. Press Enter to quit.");
                            quitAns = sc.nextLine();
                            if (quitAns.equals("")) { // enter
                                break outerLoop;
                            }
                        }
                    }
                    System.out.println("Would you like to continue? (yes/no)");
                    continueAns = sc.nextLine();
                } while (true);
            }
        }


    }

    private static boolean quitInteractive(String consentANS, Audit audit, String continueAns) {
        String quitAns;
        if (continueAns.equals("no")) {
            audit.setAuditType("User");
            audit.run();
            audit.printStatistic();
            if (consentANS.equals("yes")) { // save the user log if permitted
                audit.printToFile("./user.log");
            }
            while (true) {
                System.out.println("That’s all. Press Enter to quit.");
                quitAns =sc.nextLine();
                if (quitAns.equals("")) { // enter
                    return true;
                }
            }
        }
        return false;
    }
}











