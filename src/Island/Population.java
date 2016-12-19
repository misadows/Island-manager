package Island;

import java.util.ArrayList;

public class Population {
    private ArrayList<Creature> creatures;
    private FitnessCalculator fitnessCalculator = new FitnessCalculator();

    public Population(int populationSize){
        creatures = new ArrayList<>(populationSize);
        fitnessCalculator.setSolution("111000011010101010000111010");
    }

    public void fillPopulation(int populationSize){
        for(int i=0; i<populationSize; i++){
            creatures.add(new Creature());
        }
    }

    public int populationSize() {
        return creatures.size();
    }

    public Creature getCreature(int index) {
        return creatures.get(index);
    }

    public void saveCreature(int index, Creature indiv) {
        creatures.add(index, indiv);
    }


    public Creature getFittest() {
        if(populationSize()<1) return null;

        Creature fittest = creatures.get(0);
        for (int i = 0; i < populationSize(); i++) {
            if (fitnessCalculator.getFitness(fittest) <= fitnessCalculator.getFitness(getCreature(i))) {
                fittest = getCreature(i);
            }
        }
        return fittest;
    }
}
