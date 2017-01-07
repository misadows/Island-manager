package Topology;

import Island.IslandParams;
import Model.Result;
import Model.Topology;

import java.util.List;

public class TopologySimulator
{
    private Topology topologia;
    private List<IslandParams> islands;

    public TopologySimulator(Topology topologia)
    {
        this.topologia = topologia;
        this.islands = topologia.getIslands();
    }

    public Result startSimulation()
    {
        Result result = new Result();
        return result;
    }
}
