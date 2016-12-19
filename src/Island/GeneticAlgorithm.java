package Island;


public class GeneticAlgorithm {
    private double crossoverRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;
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
    }

    public Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), fitnessCalculator);

        if (elitism) {
            newPopulation.saveCreature(0, pop.getFittest());
        }

        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.populationSize(); i++) {
            Creature indiv1 = tournamentSelection(pop);
            Creature indiv2 = tournamentSelection(pop);
            Creature newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveCreature(i, newIndiv);
        }

        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getCreature(i));
        }

        return newPopulation;
    }

    private Creature crossover(Creature indiv1, Creature indiv2) {
        Creature newSol = new Creature();

        for (int i = 0; i < indiv1.genotypeSize(); i++) {

            if (Math.random() <= crossoverRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    private void mutate(Creature indiv) {
        for (int i = 0; i < indiv.genotypeSize(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

    private Creature tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, fitnessCalculator);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveCreature(i, pop.getCreature(randomId));
        }

        return tournament.getFittest();
    }
}
