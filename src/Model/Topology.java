package Model;

import Island.IslandParams;

import java.util.ArrayList;
import java.util.List;

public class Topology {
    private List<IslandParams> islands;
    private int connections[][];
    private int generations;

    public Topology(List<IslandParams> islands, int[][] connections, int generations)
    {
        this.islands = new ArrayList<IslandParams>();
        for(IslandParams island : islands) this.islands.add(island);
        this.connections = connections;
        this.generations = generations;
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

    public List<IslandParams> getIslands()
    {
        return islands;
    }

    public void setIslands(List<IslandParams> islands)
    {
        islands.clear();
        for(IslandParams island : islands) this.islands.add(island);
    }
}
