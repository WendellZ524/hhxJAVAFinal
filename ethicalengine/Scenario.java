package ethicalengine;

/**
 * Represents a scenario to decide on
 * COMP90041, Sem2, 2020: Final Project
 * @author: tilman.dingler@unimelb.edu.au
 */
public class Scenario {
    private boolean isLegalCrossing;
    private Persona[] passengers;
    private Persona[] pedestrians;


    public Scenario(Persona[] passengers, Persona[] pedestrians, boolean isLegalCrossing){
        this.passengers=passengers;
        this.pedestrians=pedestrians;
        this.isLegalCrossing=isLegalCrossing;
    }

    public boolean hasYouInCar(){
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i].)
        }
    }

    public boolean hasYouInLane(){

    }

    
    public Scenario() {

    }
    
}