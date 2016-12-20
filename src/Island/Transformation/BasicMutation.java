package Island.Transformation;


import Island.Creature;
import Island.FitnessCalculator;
import Island.Population;

public class BasicMutation implements TransformationInterface{
    private double rate;
    private int elitismOffset;

    public BasicMutation(double rate, int elitismOffset){
        setRate(rate);
        this.elitismOffset=elitismOffset;
    }

    @Override
    public Population transform(Population population) {
        Population newPopulation = population;

        //fix problem with elitism - sort
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getCreature(i));
        }

        return newPopulation;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    private void mutate(Creature indiv) {
        for (int i = 0; i < indiv.genotypeSize(); i++) {
            if (Math.random() <= rate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }
}
