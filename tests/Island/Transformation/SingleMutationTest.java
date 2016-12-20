package Island.Transformation;

import Island.Creature;
import org.junit.Test;

import static org.junit.Assert.*;


public class SingleMutationTest {
    @Test
    public void testMutate() {
        Creature creature = new Creature(10);
        SingleMutation singleMutation = new SingleMutation(0, 1);
        Creature mutated = new Creature(creature);
        singleMutation.mutate(mutated);

        int diffNum=0;
        for(int i=0; i<creature.genotypeSize(); i++){
            if(creature.getGene(i)!=mutated.getGene(i)) diffNum++;
        }
        assertTrue(diffNum<=1);
    }

}