package Island;

import org.junit.Test;

import static org.junit.Assert.*;


public class FitnessCalculatorTest {
    @Test
    public void getFitness()  {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        fitnessCalculator.setSolution("110");
        Creature creature = new Creature(3);
        creature.setGene(0, (byte)1);
        creature.setGene(1, (byte)1);
        creature.setGene(2, (byte)0);

        assertEquals(fitnessCalculator.getFitness(creature), 3);

        creature.setGene(1, (byte)0);
        assertEquals(fitnessCalculator.getFitness(creature), 2);
    }

    @Test
    public void getFitnessShortGenotype()  {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        fitnessCalculator.setSolution("110");
        Creature creature = new Creature(2);
        creature.setGene(0, (byte)1);
        creature.setGene(1, (byte)1);

        assertEquals(fitnessCalculator.getFitness(creature), 2);
    }

    @Test
    public void getFitnessShorttarget()  {
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        fitnessCalculator.setSolution("0");
        Creature creature = new Creature(3);
        creature.setGene(0, (byte)1);
        creature.setGene(1, (byte)1);
        creature.setGene(1, (byte)1);
        assertEquals(fitnessCalculator.getFitness(creature), 0);

        creature.setGene(0, (byte)0);
        assertEquals(fitnessCalculator.getFitness(creature), 1);
    }

}