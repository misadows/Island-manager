package Island;


public class Island implements IslandInterface{
    private IslandParams params;
    private Population population;
    private GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

    @Override
    public void setParameters(IslandParams params) {
        this.params = params;
        geneticAlgorithm.setParameters(params);
        createPopulation(params.getCreaturesNumber());
    }

    @Override
    public void sendCreature(CreatureParams params) {

    }

    @Override
    public CreatureParams getCreature() {
        return null;
    }

    @Override
    public void nextEpoch(){
        population= geneticAlgorithm.evolvePopulation(population);
        System.out.println("Fittest: " + population.getFittest());
    }

    private void createPopulation(int populationSize){
        population = new Population(populationSize);
        population.fillPopulation(populationSize);
    }
}
