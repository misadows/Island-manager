package Topology;



import java.util.ArrayList;
import java.util.List;

import Island.Creature;


public class Migration
{
    private int from;
    private int where;
    private List<Creature>  creatures = new ArrayList<Creature>();

    public Migration(int where, int from, List<Creature> creatures)
    {
        this.where = where;
        this.from = from;
        for(Creature creature : creatures) this.creatures.add(creature);
    }

    public List<Creature> getMigration()
    {
        return creatures;
    }
    

    public int getFrom()
    {
    	return from;
    }
    
    public int getWhere()
    {
    	return where;
    }
    
}
