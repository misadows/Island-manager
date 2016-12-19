package Island;


import java.util.Random;

public class Island implements IslandInterface{
    private IslandParams params;
    private Population population;
    private GeneticAlgorithm geneticAlgorithm;
    private FitnessCalculator fitnessCalculator;

    public Island(IslandParams params){
        fitnessCalculator = new FitnessCalculator();
        setParameters(params);
        geneticAlgorithm = new GeneticAlgorithm(params, fitnessCalculator);
    }

    @Override
    public void setParameters(IslandParams params) {
        this.params = params;
        fitnessCalculator.setSolution(params.getTargetSolution());
        createPopulation(params.getCreaturesNumber());
    }

    @Override
    public void sendCreature(Creature creature) {
        population.addCreature(creature);
    }

    @Override
    public Creature getCreature() {
        int size = population.populationSize();
        Random generator = new Random();
        int i = generator.nextInt(size);
        Creature creature = population.getCreature(i);
        population.removeCreature(i);
        return creature;
    }

    @Override
    public void nextEpoch(){
        population= geneticAlgorithm.evolvePopulation(population);
        System.out.println("Fittest: " + population.getFittest() +" "+ fitnessCalculator.getFitness(population.getFittest()));

    }

    private void createPopulation(int populationSize){
        population = new Population(populationSize, fitnessCalculator, params.getGenotypeSize());
        population.fillPopulation(populationSize);
    }
}
