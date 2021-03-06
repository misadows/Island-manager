package Island;


import Island.Transformation.*;

public class GeneticAlgorithm {
    private double basicCrossoverRate;
    private double basicMutationRate;
    private double singleMutationRate;
    private double singlePointCrossoverRate;

    private int tournamentSize;
    private boolean elitism;
    private int genotypeSize;
    private FitnessCalculator fitnessCalculator;

    public GeneticAlgorithm(IslandParams params, FitnessCalculator fitnessCalculator){
        setParameters(params);
        this.fitnessCalculator = fitnessCalculator;
    }

    public void setParameters(IslandParams params){
        basicCrossoverRate = params.getBasicCrossoverRate();
        basicMutationRate = params.getBasicMutationRate();
        tournamentSize = params.getTournamentSize();
        elitism = params.isElitism();

        singleMutationRate = params.getSingleMutationRate();
        singlePointCrossoverRate = params.getSinglePointCrossoverRate();

        genotypeSize = params.getGenotypeSize();
    }

    public Population evolvePopulation(Population pop) {
        int elitismOffset;
        if(elitism)  elitismOffset = 1;
        else elitismOffset = 0;

        Population newPopulation=pop;
        TransformationInterface transformations[] = {
            new BasicCrossover(basicCrossoverRate, elitismOffset, tournamentSize, fitnessCalculator, genotypeSize),
            new BasicMutation(basicMutationRate, elitismOffset, fitnessCalculator, genotypeSize),
            new SinglePointCrossover(singlePointCrossoverRate),
            new SingleMutation(singleMutationRate)
        };

        for (TransformationInterface transformation : transformations) {
            newPopulation = transformation.transform(newPopulation);
        }

        return newPopulation;
    }




}
