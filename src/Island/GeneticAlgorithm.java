package Island;


import Island.Transformation.BasicCrossover;
import Island.Transformation.BasicMutation;
import Island.Transformation.TransformationInterface;

public class GeneticAlgorithm {
    private double crossoverRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;
    private int genotypeSize;
    private FitnessCalculator fitnessCalculator;

    public GeneticAlgorithm(IslandParams params, FitnessCalculator fitnessCalculator){
        setParameters(params);
        this.fitnessCalculator = fitnessCalculator;
    }

    public void setParameters(IslandParams params){
        crossoverRate = params.getCrossoverRate();
        mutationRate = params.getMutationRate();
        tournamentSize = params.getTournamentSize();
        elitism = params.isElitism();
        genotypeSize = params.getGenotypeSize();
    }

    public Population evolvePopulation(Population pop) {
        int elitismOffset;
        if(elitism)  elitismOffset = 1;
        else elitismOffset = 0;

        Population newPopulation=pop;
        TransformationInterface transformations[] = {
            new BasicCrossover(crossoverRate, elitismOffset, tournamentSize, fitnessCalculator, genotypeSize),
            new BasicMutation(mutationRate, elitismOffset, fitnessCalculator, genotypeSize)
        };

        for (TransformationInterface transformation : transformations) {
            newPopulation = transformation.transform(newPopulation);
        }

        return newPopulation;
    }




}
