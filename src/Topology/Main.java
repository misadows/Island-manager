package Topology;


import Model.Topology;
import Topology.Listener;
import Topology.TopologySimulator;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import Topology.Messages.StartSimulation;

import java.util.ArrayList;
import java.util.List;

import Island.Island;
import Island.IslandParams;

public class Main 
{
	private List<Island> islands = new ArrayList<Island>();
	private int[][] connections;
	private Topology topology;
	
	public Main()
	{
		 islands.add(new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
	                "1110000110101010100001110100110101111000101110101101010110111101")));
		 islands.add(new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
	                "1110000110101010100001110100110101111000101110101101010110111101")));
		 islands.add(new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
	                "1110000110101010100001110100110101111000101110101101010110111101")));
		 islands.add(new Island(new IslandParams(100, 0.1, 0.5, 0.1, 0.5, 0.1, 40, true,
	                "1110000110101010100001110100110101111000101110101101010110111101")));
		connections = new int[4][4];
		topology = new Topology(islands, connections);
	}
	
	 public static void main(String[] args)
	 {
		 Main simulation = new Main();
		 simulation.simulate(100);
		 
	 }
		 
		 
		  public void simulate(final int generations) 
		  {
			  	
			    ActorSystem system = ActorSystem.create("Simulation");
			 
			    //final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");
			 
			    ActorRef master = system.actorOf(new Props(
	            new UntypedActorFactory() 
			    {
			      public UntypedActor create() 
			      {
			        return new TopologySimulator(topology);
			      }
			    }), "master");
			 
			    master.tell(new StartSimulation(), master);
		    
		  }
		 
}
