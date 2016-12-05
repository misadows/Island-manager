package topologia;

import java.util.ArrayList;
import java.util.List;

public class Topology
{
	private List<Island> islands;
	private int connections[][];
	
	public Topology(List<Island> islands, int[][] connections)
	{
		islands = new ArrayList<Island>();
		for(Island island : islands) this.islands.add(island);
		
		
	}
	
	public int[][] getConnections()
	{
		return connections;
	}
	
	public void setConnections(int connections[][])
	{
		for(int i=0; i<connections.length; i++)
			for(int j=0;j<connections[i].length;j++)
			{
				this.connections[i][j] = connections[i][j];
			}
	}
	
	public List<Island> getIslands()
	{
		return islands;
	}
	
	public void setIslands(List<Island> islands)
	{
		islands.clear();
		for(Island island : islands) this.islands.add(island);
	}
}

