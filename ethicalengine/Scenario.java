package ethicalengine;

/**
 * Represents a scenario to decide on
 * COMP90041, Sem2, 2020: Final Project
 *
 * @author: tilman.dingler@unimelb.edu.au
 */
public class Scenario {
    private boolean legalCrossing;
    private Persona[] passengers;
    private Persona[] pedestrians;
    private boolean inLane=false;


    public Scenario(Persona[] passengers,Persona[] pedestrians, boolean isLegalCrossing) {
        this.passengers = passengers;
        this.pedestrians = pedestrians;
        this.legalCrossing = isLegalCrossing;
    }

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
     * @return a boolean indicating whether you (the user) are in the
     * lane, i.e., crossing the street.
     */
    public boolean hasYouInLane() {
        return inLane;
    }

    /**
     * @return the cars’ passengers as a Persona[] array.
     */
    public Persona[] getPassengers(){
        return this.passengers;
    }

    /**
     * @return the pedestrians as a Persona[] array.
     */
    public Persona[] getPedestrians(){
        return this.pedestrians;
    }

    /**
     * @return whether the pedestrians are legally crossing
     * at the traffic light.
     */
    public boolean isLegalCrossing(){
        return legalCrossing;
    }

    /**
     * sets whether the pedestrians are legally crossing the street.
     * @param isLegalCrossing
     * @return
     */
    public void setLegalCrossing(boolean isLegalCrossing){
        this.legalCrossing=isLegalCrossing;
    }

    /**
     * @return the number of passengers in the car (in int).
     */
    public int getPassengerCount(){
        return passengers.length;
    }

    /**
     * @return the number of pedestrians in the car (in int).
     */
    public int getPedestrianCount(){
        return pedestrians.length;
    }

    /**
     * Output the passenger details
     * @return the formatted each passenger detial (in String)
     */
    public String outputPassengers(){
        String temp="";
        for(Persona i: getPassengers()){
            temp+="- ";
            temp+=i.toString();
            temp+="\n";
        }
    return temp;}

    /**
     * Output the pedestrians details
     * @return the formatted each peds detial (in String)
     */
    public String outputPedestrians(){
        String temp="";
        for(Persona i: getPedestrians()){
            temp+="- ";
            temp+=i.toString();
            temp+="\n";
        }
        return temp;}

    public String toString(){
        String legalCrossingState;
        if(legalCrossing){
            legalCrossingState="yes";
        }
        else {
            legalCrossingState="no";
        }
        String format = "======================================\n"
                + "# Scenario\n" +
                "======================================\n";
        return format+"Legal Crossing: " +legalCrossingState +"\n"
                +"Passengers (" +getPassengerCount()+")"+"\n"
                + outputPassengers()+"\n"+"Pedestrians ("
                +getPassengerCount()+")"+"\n"+outputPedestrians();
    }


    public Scenario() {

    }

}