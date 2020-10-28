import ethicalengine.*;

/**
 * COMP90041, Sem2, 2020: Final Project: A skeleton code for you to update
 * @author: tilman.dingler@unimelb.edu.au
 */
public class EthicalEngine {

    public enum Decision {PASSENGERS, PEDESTRIANS};

    /**
     * Decides whether to save the passengers or the pedestrians
     * @param Scenario scenario: the ethical dilemma
     * @return Decision: which group to save
     */
    public static Decision decide(Scenario scenario) {
        // a rather random decision engine
        // TOOD: take into account at least 5 scenario characteristics
        if (Math.random() > 0.5) {
            return Decision.PASSENGERS;
        } else {
            return Decision.PEDESTRIANS;
        }
    }

    public static void main(String[] args) {
    	System.out.println(decide(null).toString() + " survive.");
    }
}