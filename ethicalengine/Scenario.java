package ethicalengine;

/**
 * COMP90041, Sem2, 2020: Final Project:
 * The class contains the scenario information in a simulation
 *
 * @author: HAIXIANG HUANG
 * student id: 965205
 * username: HAIXIANGH
 * Github repository link:
 * https://github.com/COMP90041/final-project-moral-machines-haixiangh.git
 */
public class Scenario {
    private boolean legalCrossing;
    // this variable is for interactive mode setting that allow passenger live
    private int passengerLiveWeight = 0;
    private Persona[] passengers;
    private Persona[] pedestrians;
    private boolean inLane = false;

    /**
     * The setter for passenger array.
     *
     * @param passengers a Persona array
     */
    public void setPassengers(Persona[] passengers) {
        this.passengers = passengers;
    }

    /**
     * The setter for pedestrian array.
     *
     * @param pedestrians a Persona array
     */
    public void setPedestrians(Persona[] pedestrians) {
        this.pedestrians = pedestrians;
    }

    /**
     * The getter for passenger
     *
     * @return the cars’ passengers as a Persona[] array.
     */
    public Persona[] getPassengers() {
        return this.passengers;
    }

    /**
     * The getter for passenger
     *
     * @return the pedestrians as a Persona[] array.
     */
    public Persona[] getPedestrians() {
        return this.pedestrians;
    }

    /**
     * This is for interactive mode to set the weight of the passenger can live
     *
     * @param passengerLiveWeight
     */
    public void setPassengerLiveWeight(int passengerLiveWeight) {
        this.passengerLiveWeight = passengerLiveWeight;
    }

    /**
     * The getter to get the weight of the passenger can live
     *
     * @return the weight of the passenger can live
     */
    public int getPassengerLiveWeight() {
        return passengerLiveWeight;
    }

    /**
     * The empty constructor for scenario
     */
    public Scenario() {
    }

    /**
     * The constructor for scenario
     *
     * @param passengers      a Persona[]
     * @param pedestrians     a Persona[]
     * @param isLegalCrossing the state of if the pedestrian are crossing legally, True if so.
     */
    public Scenario(Persona[] passengers, Persona[] pedestrians, boolean isLegalCrossing) {
        this.passengers = passengers;
        this.pedestrians = pedestrians;
        this.legalCrossing = isLegalCrossing;
    }

    /**
     * The getter indicating user is in the simulation as passenger
     *
     * @return a boolean indicating if user is in car.
     */
    public boolean hasYouInCar() {
        boolean InCar = false;
        for (Persona i : passengers) {
            // downcasting
            if (((Human) i).isYou()) {
                InCar = true;
                break;
            }
        }
        return InCar;
    }

    /**
     * @return a boolean indicating whether the user is in the
     * lane, i.e., crossing the street.
     */
    public boolean hasYouInLane() {
        return inLane;
    }


    /**
     * @return whether the pedestrians are legally crossing
     * at the traffic light.
     */
    public boolean isLegalCrossing() {
        return legalCrossing;
    }

    /**
     * sets whether the pedestrians are legally crossing the street.
     *
     * @param isLegalCrossing if the user is crossing legally. True if is legal.
     */
    public void setLegalCrossing(boolean isLegalCrossing) {
        this.legalCrossing = isLegalCrossing;
    }

    /**
     * @return the number of passengers in the car (in int).
     */
    public int getPassengerCount() {
        return passengers.length;
    }

    /**
     * @return the number of pedestrians in the car (in int).
     */
    public int getPedestrianCount() {
        return pedestrians.length;
    }

    /**
     * Output the passenger details
     *
     * @return the formatted each passenger detail (in String)
     */
    public String outputPassengers() {
        String temp = "";
        for (int i = 0; i < getPassengers().length - 1; i++) {
            temp += "- ";
            temp += getPassengers()[i].toString();
            temp += "\n";
        }
        // the last one doesn't need a blank space
        temp += "- " + getPassengers()[getPassengers().length - 1];
        return temp;
    }

    /**
     * Output the pedestrians details
     *
     * @return the formatted each pedestrian detail (in String)
     */
    public String outputPedestrians() {
        String temp = "";
        for (int i = 0; i < getPedestrians().length - 1; i++) {
            temp += "- ";
            temp += getPedestrians()[i].toString();
            temp += "\n";
        }
        // the last one doesn't need blank space
        temp += "- " + getPedestrians()[getPedestrians().length - 1];
        return temp;
    }

    /**
     * @return Output if pedestrians are crossing legally, passenger and pedestrians detail.
     */
    public String toString() {
        String legalCrossingState;
        if (legalCrossing) {
            legalCrossingState = "yes";
        } else {
            legalCrossingState = "no";
        }
        String format = "======================================\n"
                + "# Scenario\n" +
                "======================================\n";
        return format + "Legal Crossing: " + legalCrossingState + "\n"
                + "Passengers (" + getPassengerCount() + ")" + "\n"
                + outputPassengers() + "\n" + "Pedestrians ("
                + getPedestrianCount() + ")" + "\n" + outputPedestrians();
    }
}