package Topology;


import Island.Creature;

import java.util.List;

public class Migration
{
    private int from;
    private int where;
    private List<Creature> creatures;

    public Migration(int where, int from, List<Creature> creatures)
    {
        this.where = where;
        this.from = from;
        //for(Creature island : creatures) this.creatures.add(creature);
    }

    public void doMigration()
    {
        
    }
}
