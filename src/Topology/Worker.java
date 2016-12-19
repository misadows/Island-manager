package Topology;

import akka.actor.UntypedActor;
import scala.util.Random;

import java.util.ArrayList;
import java.util.List;
import Island.CreatureParams;
import Island.Results;
import Model.Island;
import Topology.Messages.*;


//Creating the worker
	public class Worker extends UntypedActor
	{
		private int id;
		private Island island;
		private int generation;
		private int generations;
		private Results results = new Results();
		
		private List<CreatureParams> population;

		public Worker()
		{
			generation = 0;
			population = new ArrayList<CreatureParams>();
			for(int i=0;i<100;i++) population.add(new CreatureParams());
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
		
		public List<CreatureParams> getPopulation()
		{
			return population;
		}
		
		
		public void increasePopulation(Migration migration)
		{
			if(migration.getWhere() == getId())
			this.population.addAll(migration.getMigration());
		}
		
		public void decreasePopulation(Migration migration)
		{
			this.population.removeAll(migration.getMigration());
		}
		
		public int getId()
		{
			return id;
		}
		
		private void Simulate(int generations) 
		{
			Random generator = new Random();
			double result = 1;
			if(generation > generations)
			{
				getSender().tell(results, this.getSelf());
			}
			
			
			System.out.println(getId()+" "+this.getPopulation().size());
			/*
			 population.mutate
			 population.evaluate
			 */
				
				
			if(generation == 10)
			{
				int where = generator.nextInt(4);
				int numberOfCreatures = generator.nextInt(population.size());
				List<CreatureParams> migrationPopulation = new ArrayList<>();
				for(int i=0;i<numberOfCreatures;i++) migrationPopulation.add(population.get(i));
				if(where != getId())
				{
					Migration migration = new Migration(where, this.getId(), migrationPopulation);
					getSender().tell(migration, getSelf());
					this.decreasePopulation(migration);
				}
			}
			generation++;
			getSender().tell(new CheckMigrations(), this.getSelf());
		}
		
		
		public void onReceive(Object message)
		{
			if(message instanceof Work)
			{
				Work work = (Work) message;
				
				Simulate(work.getGenerations());
			}
			
			if(message instanceof SetWorker)
			{
				SetWorker params = (SetWorker) message;
				this.setId(params.getId());
				this.setIsland(params.getIsland());
				
			}
				
			if(message instanceof Migration)
			{
				Migration p = (Migration) message;
				this.increasePopulation(p);
			}
			
			if(message instanceof WorkNext)
			{
				Simulate(this.generations);
			}
		}
	}