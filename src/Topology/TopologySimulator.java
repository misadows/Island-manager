package Topology;

import Model.Island;
import Model.Result;
import Model.Topology;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.List;
import Topology.Messages.*;


public class TopologySimulator extends UntypedActor
{
    private Topology topology;
    private List<Island> islands = new ArrayList<Island>();
    private ActorRef workers[];
    private MigrationManager migrationManager;
    private List<Migration> currentMigrations = new ArrayList<Migration>();
    private Result result = new Result();

    public TopologySimulator(Topology topology)
    {
    	this.topology = topology;
    	this.islands.addAll(topology.getIslands());
        this.migrationManager = new MigrationManager();
        this.workers = new ActorRef[4];
        
        
        for(int i=0; i<4;i++) workers[i] = this.getContext().actorOf(new Props(Worker.class), "worker"+i);
    }

	@Override
	public void onReceive(Object message) 
	{
		
		if(message instanceof Migration)
		{
			Migration migration = (Migration) message;
			currentMigrations.add(migration);
			migrationManager.addMigration(migration);
		}	
		
		  if (message instanceof StartSimulation) 
		  {
			  int i=0; 
			  List<ActorRef> w = new ArrayList<ActorRef>();
			  for(ActorRef worker : workers) w.add(worker); 
			  for(ActorRef worker : workers)
			  {
				  worker.tell(new SetWorker(i, islands.get(i)), getSelf());
				  ++i;
			  }
			  for(ActorRef worker : workers) worker.tell(new Work(100), getSelf());
			
		  }
		  
		  if(message instanceof CheckMigrations)
		  {
			  for(Migration migration : currentMigrations)
			  {
				  for(ActorRef worker : workers)
				  {
					  worker.tell(migration, this.getSelf());
				  }
				  
			  }
			  currentMigrations.clear();
			  for(ActorRef worker : workers)
			  {
				  worker.tell(new WorkNext(), this.getSelf());
			  }
		  }
		  
		  
		  if (message instanceof Result) 
		  {
			
		  } 
		  else 
		  {
		    unhandled(message);
		  }
		      
	}
    
}
