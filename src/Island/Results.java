package Island;


import Model.Result;

import java.util.ArrayList;

public class Results {
    private ArrayList<EpochResult> epochResults;
    public Results() {
        epochResults = new ArrayList<EpochResult>();
    }

    public ArrayList<EpochResult> getEpochResults() {
        return epochResults;
    }

    public void addResult(Population population, FitnessCalculator fitnessCalculator){
        epochResults.add(new EpochResult(population, fitnessCalculator));
    }
}
