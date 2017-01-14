package Visualisation;

import Island.Island;
import Island.IslandParams;

import java.util.ArrayList;


public class MockResults {
    //  Class only for test charts
    //  Mock islands data

    public static int ISLAND_NUMBER = 4;
    public static final String TARGET_SIZE = "1110000110101010100001110100110101111000101110101101010110111101";

    private ArrayList<Integer>[] islandResults = new ArrayList[ISLAND_NUMBER];
    public MockResults(int generations) {
        for(int j=0; j < ISLAND_NUMBER; j++){
            Island island = new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
                    TARGET_SIZE));

            for(int i=0; i<generations; i++){
                island.nextEpoch();
            }
            islandResults[j] = new ArrayList<>();
            for(int i=0; i<island.getResults().getEpochResults().size(); i++){
                // Get population series
                System.out.println("Population size: " + island.getResults().getEpochResults().get(i).getPopulation().populationSize());
                islandResults[j].add(i, island.getResults().getEpochResults().get(i).getPopulation().populationSize());
            }
        }
    }

    public ArrayList<Integer>[] getIslandResults() {
        return islandResults;
    }
}
