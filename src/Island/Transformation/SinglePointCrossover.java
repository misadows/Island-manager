package Island.Transformation;

import Island.Creature;
import Island.FitnessCalculator;
import Island.Population;


public class SinglePointCrossover implements TransformationInterface {
    private double rate;
    private int elitismOffset;
    private int tournamentSize;
    private FitnessCalculator fitnessCalculator;
    private int genotypeSize;

    public SinglePointCrossover(double rate, int elitismOffset, int tournamentSize, FitnessCalculator fitnessCalculator, int genotypeSize){
        setRate(rate);
        this.elitismOffset = elitismOffset;
        this.tournamentSize = tournamentSize;
        this.fitnessCalculator = fitnessCalculator;
        this.genotypeSize = genotypeSize;
    }

    @Override
    public Population transform(Population population) {
        return population;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    public Creature crossover(Creature indiv1, Creature indiv2) {
        Creature newSol = new Creature(genotypeSize);

        for (int i = 0; i < indiv1.genotypeSize(); i++) {

            if (Math.random() <= rate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
                break;
            }
        }
        return newSol;
    }

    public Creature tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, fitnessCalculator, genotypeSize);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveCreature(i, pop.getCreature(randomId));
        }

        return tournament.getFittest();
    }
}
