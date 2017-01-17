package Topology;

import Island.Island;
import Island.Creature;
import Island.EpochResult;
import Model.Result;
import Model.Topology;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import java.util.concurrent.TimeUnit;

import Topology.Messages.*;


public class TopologySimulator extends UntypedActor
{	
	
	private final int LICZBA_WYSP = 4;
	private String worker = "worker";
	private boolean isMigration;
	private int currentGeneration;
	private int generations;
	private int workCounter;
	private int migrationCounter;
	private int migrationEnd;
    private Topology topology;
    private List<Island> islands = new ArrayList<Island>();
    private ActorRef workers[];
   
    private MigrationManager migrationManager;
    private List<Migration> currentMigrations = new ArrayList<Migration>();
    private Result result = new Result();
	private int[] where;
	private int[] from;
    public TopologySimulator(Topology topology)
    {	
    	isMigration=false;
    	currentGeneration=0;
    	migrationEnd=0;
    	generations=10;
    	workCounter=0;
    	migrationCounter=0;
    	this.topology = topology;
    	this.islands.addAll(topology.getIslands());
        this.migrationManager = new MigrationManager();
        this.workers = new ActorRef[4];
        this.where = new int[LICZBA_WYSP];
        this.from = new int[LICZBA_WYSP];
        
        System.out.println("generacja 0");
        for(int i=0; i<LICZBA_WYSP;i++) workers[i] = this.getContext().actorOf(new Props(Worker.class), worker+i);
    }
    
    
	@Override
	public void onReceive(Object message) //throws InterruptedException 
	{		  
		
		
		if (message instanceof StartSimulation) 
	  {
		  int i=0; 
		  List<ActorRef> w = new ArrayList<ActorRef>();
		  for(ActorRef worker : workers) w.add(worker); 
		  for(ActorRef worker : workers)
		  {
			  worker.tell(new SetWorker(i, islands.get(i),generations), getSelf());
			  ++i;
		  }
		  currentGeneration++;
		  for(ActorRef worker : workers) worker.tell(new Work(), getSelf());
		
	  }
		
		if (message instanceof WorkDone){
			Random generator = new Random();
			workCounter++;
			if(workCounter==LICZBA_WYSP){
				workCounter=0;
				
				//zrob migracje
				if( generator.nextInt(2)==1){
					for(int i=0;i<LICZBA_WYSP;i++){ 
						from[i]=i;
					}
					
					for(int i=0;i<LICZBA_WYSP;i++){ 
						where[i]=i;
					}
					migrationCounter=generator.nextInt(LICZBA_WYSP);
					migrationEnd=migrationCounter+1;
					System.out.println(migrationCounter+1);
					
					int tmp=LICZBA_WYSP;
					for(int i=0;i<=migrationCounter;i++){ //from (liczby nie moga sie powtarzac)
						int val=generator.nextInt(tmp);
						tmp--;
						int tmp2;
						tmp2=from[tmp];
						from[tmp]=from[val];
						from[val]=tmp2;
						
					}
					tmp=LICZBA_WYSP;
					for(int i=0;i<=migrationCounter;i++){ //where
						int val=generator.nextInt(tmp);
						tmp--;
						where[tmp]=val;
					}
					tmp=LICZBA_WYSP;
					for(int i=0;i<=migrationCounter;i++){
						tmp--;
						if(where[tmp]!=from[tmp]){
							isMigration=true;
							System.out.println("skad "+from[tmp] +" dokad "+where[tmp]);
							workers[from[tmp]].tell(new getCreature(where[tmp]),getSelf());
					}
						else{
							System.out.println("nie mozna migrowac na ta sama wyspe ");
							migrationEnd--;
						}
		
					}
				}
				
				if(!isMigration){	
					isMigration=false;
					//kolejna generacja 
					if(currentGeneration<generations){
					System.out.println("generacja "+ currentGeneration);
					for(ActorRef worker : workers) worker.tell(new Work(), getSelf());
					}
					else 
					{
						//System.out.println(currentGeneration + "bez mig");
						
						 for(int i=0; i<islands.get(3).getResults().getEpochResults().size(); i++){
					     System.out.println("Fitness: " + islands.get(3).getResults().getEpochResults().get(i).getMaxFitness());}
						//System.out.println("Fitness: " + islands.get(3).getResults().getEpochResults().get(9).getMaxFitness());
						getContext().system().shutdown();
					}
					currentGeneration++;
				}
			}
		}
		if(message instanceof returnMigration){
			System.out.println("odebrano kreature");
			returnMigration params= (returnMigration) message;
			int where=params.getWhere();
			int from=params.getFrom();
			Creature creature=params.getCreature();
			workers[where].tell(new sendCreature(from, creature),getSelf());
		}
		if(message instanceof MigrationDone){
				//odbierz migracje
			 //zapisz migracje 
			 MigrationDone migrationDone = (MigrationDone) message;
			 migrationDone.getMigration().setGeneration(currentGeneration);
			 migrationManager.addMigration(migrationDone.getMigration());
			 System.out.println("migrationdone");
				migrationEnd--;
				if(migrationEnd==0){
					System.out.println("zakonczono migracje");

					//kolejna generacja
					isMigration=false;
					if(currentGeneration<generations){
					System.out.println("generacja "+ currentGeneration);
					for(ActorRef worker : workers) worker.tell(new Work(), getSelf());
					}
					
					else
					{
						//System.out.println(currentGeneration);
						for(int i=0; i<islands.get(3).getResults().getEpochResults().size(); i++)
						{
						     System.out.println("Fitness: " + islands.get(3).getResults().getEpochResults().get(i).getMaxFitness());
						}
						//migrationManager.concatenateMigration();
						getContext().system().shutdown();
					}
					currentGeneration++;
				}
		}
		
		
		  else 
		  {
		    unhandled(message);
		  }
		      
	}
    
}
