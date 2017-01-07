package Visualisation;

import Model.Island;
import Model.Result;

import java.util.List;

public interface IVisualisation {
    public static final int ISLAND_NUMBER = 4;

    public void setPrecision();

    //It will be seperated to some data provider class
    String getFunctionPattern();
    int getStartRange();
    int getEndRange();
    int[] getMigrationsTime();
    int[] getMigrationsRate();
    int[] getMutationsProbability();
    String[] getCrossoverOperator();
    String[] getMutationOperator();
    //It is not exactly specified what the "Island" model needed yet
    void setIslands(String function, int startRange, int endRange, int[] migrationsTime, int[] migrationsRate,
                    int[] mutationsProbability, String[] crossoverOperator, String[] mutationOperator);

    int[][] getConnections();
    void setTopology(List<Island> islands, int[][] connections);

    void initialize();
    Result startAlgorithm();
    void startAnimation(Result result);


}
