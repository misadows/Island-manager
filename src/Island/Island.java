package Island;

import java.util.ArrayList;

public class Population {
    private ArrayList<Creature> creatures;
    private FitnessCalculator fitnessCalculator;
    private int genotypeSize;

    public Population(int populationSize, FitnessCalculator fitnessCalculator, int genotypeSize){
        creatures = new ArrayList<>(populationSize);
        this.fitnessCalculator = fitnessCalculator;
        this.genotypeSize = genotypeSize;
    }

    public void fillPopulation(int populationSize){
        for(int i=0; i<populationSize; i++){
            creatures.add(new Creature(genotypeSize));
        }
    }
    public ArrayList<Creature> getCreatures(){
    	return creatures;
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

    public void addCreature(Creature creature){
        creatures.add(creature);
    }
<<<<<<< HEAD
=======
    public Population getPopulation(){
    	return population;
    }
>>>>>>> topology

    public void removeCreature(int index){
        creatures.remove(index);
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
