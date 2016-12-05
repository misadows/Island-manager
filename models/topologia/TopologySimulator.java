package topologia;

import java.util.List;

public class TopologySimulator
{
	Topologia topologia;
	List<Island> islands;
	
	public TopologySimulator(Topologia topologia)
	{
		this.topologia = topologia;
		this.islands = topologia.getIslands();
	}
	
	public Results startSimulation()
	{
		return results;
	}
}