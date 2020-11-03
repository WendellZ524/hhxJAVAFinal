import ethicalengine.Persona;
import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;


import java.util.ArrayList;

public class Audit {
    private String auditType = "Unspecified";
    private static ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
    private int roundCounts=0;

    // 这些list后面要放到方法外面
    ArrayList<Integer> maleList = new ArrayList<>();
    ArrayList<Integer> femaleList = new ArrayList<>();


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
        roundCounts=runs;
    }

    public void createCharacteristicLists() {
        // Audit为每一个种类（Body，baby）开一个list，0生1死

        // 要先用ethicalengine决定生死
        Persona[] passenger;
        for (Scenario i : scenarioList) {
            // run Ethical Engine's decide method
            String groupCanLive = EthicalEngine.decide(i).toString();
            if (groupCanLive.equals("PASSENGERS")) {
                // get passenger array in each scenario
                passenger = i.getPassengers();
                for(Persona j:passenger){
                    System.out.println(j);
                }
            } else {
                System.out.println("乘客死了");
            }
        }
    }




    public String toString() {
        String title="======================================\n" +
                "# "+ auditType +" Audit\n" +
                "======================================\n" +
                "- % SAVED AFTER "+ roundCounts+" RUNS\n";

        String result="";
        result+=title;
        if(! scenarioList.isEmpty()) {
            for (Scenario i : scenarioList) {
                result += i;
            }
        }
        else {
            result="no audit available";
        }
    return result;}
}
