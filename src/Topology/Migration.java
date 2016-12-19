package Topology;



import java.util.ArrayList;
import java.util.List;
import Island.CreatureParams;

public class Migration
{
    private int from;
    private int where;
    private List<CreatureParams>  creatures = new ArrayList<CreatureParams>();

    public Migration(int where, int from, List<CreatureParams> creatures)
    {
        this.where = where;
        this.from = from;
        for(CreatureParams creature : creatures) this.creatures.add(creature);
    }

    public List<CreatureParams> getMigration()
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
