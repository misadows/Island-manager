package Topology;

import akka.actor.UntypedActor;


import java.util.ArrayList;
import java.util.List;

import Island.Creature;

import Island.Results;
import Island.Island;
import Topology.Messages.*;


//Creating the worker
	public class Worker extends UntypedActor
	{
		private int id;
		private Island island;
		private int generation;
		private int generations;
		private Results results = new Results();
		
		

		public Worker()
		{
			generation = 0;
	
		}
		
		public int getGenerations()
		{
			return generations;
		}
		
		public void setGenerations(int generations)
		{
			this.generations = generations;
		}
		
		public void setId(int newId)
		{
			id = newId;
		}
		
		public void setIsland(Island island)
		{
			this.island = island;
		}
		
		public Island getIsland()
		{
			return island;
		}
	
		public int getId()
		{
			return id;
		}
		
		
		private void Simulate() 
		{
			
			
				
			ArrayList<Creature> x=island.getPopulation().getCreatures();
			 
	        
				System.out.println(getId()+" "+x.size() +" "+ generation);
				this.island.nextEpoch();
				generation++;
				getSender().tell(new WorkDone(), this.getSelf());
				
				
	
		}
		
		
		public void onReceive(Object message)
		{
		
			
			if(message instanceof SetWorker)
			{
				SetWorker params = (SetWorker) message;
				this.setId(params.getId());
				this.setIsland(params.getIsland());
				this.setGenerations(params.getGenerations());
			}
			
			if(message instanceof Work)
			{
				//Work work = (Work) message;
				Simulate();
			}
			
			if(message instanceof getCreature){
				getCreature params= (getCreature) message;
				int where=params.getWhere();
				//pobierz kreature
				 Creature creature=this.island.getCreature();
				getSender().tell(new returnMigration(where, this.getId(),creature), this.getSelf());
			}
			
			
			if(message instanceof sendCreature){
				sendCreature params= (sendCreature) message;
				Creature creature = params.getCreature();
				this.island.sendCreature(creature);
				
				List<Creature> creatures = new ArrayList<Creature>();
				creatures.add(params.getCreature());
				Migration migration = new Migration(this.getId(), params.getFrom(), creatures);
				getSender().tell(new MigrationDone(migration), this.getSelf());
			}
			
		}
	}
