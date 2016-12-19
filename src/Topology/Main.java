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

import Model.Island;

public class Main 
{
	private List<Island> islands;
	private int[][] connections;
	private Topology topology;
	
	 public static void main(String[] args)
	 {
		 Main simulation = new Main();
		 simulation.simulate(100);
	 }
		 
		 
		  public void simulate(final int generations) 
		  {
			  	islands = new ArrayList<Island>();
				for(int i=0;i<4;i++) islands.add(new Island());
				connections = new int[4][4];
				topology = new Topology(islands, connections);
			    ActorSystem system = ActorSystem.create("Simulation");
			 
			    final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");
			 
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
