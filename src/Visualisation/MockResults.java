package Visualisation;

import Island.Island;
import Island.IslandParams;
import Model.Migration;

import java.util.ArrayList;
import java.util.Random;


public class MockResults {
    //  Class only for test charts
    //  Mock islands data

    public static int ISLAND_NUMBER = 4;
    public static final String TARGET_SIZE = "1110000110101010100001110100110101111000101110101101010110111101";
    // 20. Dodatkowe: Wyciągnąć wszystko do stałych
    private ArrayList<Integer>[] islandResults = new ArrayList[ISLAND_NUMBER];
    private ArrayList<Migration> migrations;
    private int generations;
    public MockResults(int generations) {
        this.generations = generations;
        for(int j=0; j < ISLAND_NUMBER; j++){
            Island island = new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
                    TARGET_SIZE));

            for(int i=0; i<generations; i++){
                island.nextEpoch();
            }
            islandResults[j] = new ArrayList<>();
            for(int i = 0; i<island.getResults().getEpochResults().size()*3; i++) islandResults[j].add(0);
            for(int i = 0; i<island.getResults().getEpochResults().size(); i++){
                // Get population series
                System.out.println("Population size: " + island.getResults().getEpochResults().get(i).getPopulation().populationSize());
                System.out.println("Max Fitness: " + island.getResults().getEpochResults().get(i).getMaxFitness());
                System.out.println("Average Fitness: " + island.getResults().getEpochResults().get(i).getAverageFitness());
                islandResults[j].add(i, island.getResults().getEpochResults().get(i).getPopulation().populationSize());
                islandResults[j].add((i+island.getResults().getEpochResults().size()), island.getResults().getEpochResults().get(i).getMaxFitness());
                islandResults[j].add((i+2*island.getResults().getEpochResults().size()), (int)island.getResults().getEpochResults().get(i).getAverageFitness());
            }
        }
        migrations = new ArrayList<>();
        for(int k=0; k<generations; k++){
            Random generator = new Random();
            migrations.add(new Migration(generator.nextInt(4), generator.nextInt(4), k));
        }
    }
    public ArrayList<Migration> getMigrations() { return migrations; }
    public int getGenerations() { return generations; }
    public ArrayList<Integer>[] getIslandResults() {
        return islandResults;
    }
}
