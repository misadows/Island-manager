package Island.Transformation;

import Island.Creature;
import Island.FitnessCalculator;
import Island.Population;
import org.junit.Test;

import static org.junit.Assert.*;


public class BasicCrossoverTest {
    @Test
    public void testCrossover(){
        Creature creature1 = new Creature(3);
        creature1.setGene(0, (byte)1);
        creature1.setGene(1, (byte)1);
        creature1.setGene(2, (byte)0);

        Creature creature2 = new Creature(3);
        creature2.setGene(0, (byte)0);
        creature2.setGene(1, (byte)1);
        creature2.setGene(2, (byte)0);

        BasicCrossover basicCrossover = new BasicCrossover(0.5, 1, 1, new FitnessCalculator(), 3);
        Creature child = basicCrossover.crossover(creature1, creature2);

        for(int i=0; i<3; i++){
            assertTrue(child.getGene(i) == creature1.getGene(i) || child.getGene(i) == creature2.getGene(i));
        }
    }

    @Test
    public void testTournament(){
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        fitnessCalculator.setSolution("1100011010");

        Population population = new Population(5, fitnessCalculator, 10);
        population.fillPopulation(5);

        BasicCrossover basicCrossover = new BasicCrossover(0.5, 1, 3, fitnessCalculator, 10);
        Creature selectedCreature = basicCrossover.tournamentSelection(population);

        boolean oneEquals=false;
        for(int i=0; i<population.populationSize(); i++){
            Creature c = population.getCreature(i);
            boolean equals=true;
            for(int j=0; j<c.genotypeSize(); j++){
                if(c.getGene(j)!=selectedCreature.getGene(j)) equals=false;
            }
            oneEquals |= equals;
        }
        assertTrue(oneEquals);

    }
}