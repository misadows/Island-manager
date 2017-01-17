package Topology;

import Island.Creature;
import Island.Island;

public class Messages
{
	//creating the messages
	static class StartSimulation
	{
		
	}
	
	static class Work
	{
				
	}
	
	
	static class SetWorker
	{	
		private final int generations;
		private final int id;
		private final Island island;
		
		public SetWorker(int id, Island island,int generations)
		{
			this.generations=generations;
			this.id = id;
			this.island = island;
		}
		
		public int getGenerations()
		{
			return generations;
		}

		
		
		public int getId()
		{
			return id;
		}
		
		public Island getIsland()
		{
			return island;
		}
		
	}
	static class WorkDone{
		
	}
	
	static class MigrationDone
	{
		private Migration migration;
		
		public MigrationDone(Migration migration)
		{
			this.migration = new Migration(migration.getWhere(), migration.getFrom(), migration.getMigration());
		}
		
		public Migration getMigration()
		{
			return migration;
		}
	}
	
	static class returnMigration{
		private int where;
		private int from;
		private Creature creature;
		public returnMigration(int where, int from, Creature creature){
			this.where=where;
			this.from = from;
			this.creature=creature;
		}
		public void setWhere(int where)
		{
			this.where=where;
		}
		public int getWhere()
		{
			return where;
		}
		public int getFrom()
		{
			return from;
		}
		public Creature getCreature(){
			return creature;
		}
	}
	
	static class sendCreature{
		private int from;
		private Creature creature;
		
		public sendCreature(int from, Creature creature){
			this.from = from;
			this.creature= creature;
		}
		
		public int getFrom()
		{
			return from;
		}
		
		public Creature getCreature(){
			return creature;
		}
	}
	
	static class getCreature{
	 private int where;
	 
	 public getCreature(int where){
		 this.where=where;
	 }
	 public void sendCreature(int where)
		{
			this.where=where;
		}
	 public int getWhere()
		{
			return where;
		}
	}
	/*
	static class FinishWork
	{
		private double result;
		
		public FinishWork(double result)
		{
			this.result = result;
		}
		
		public double getResult()
		{
			return result;
		}
	}
	
	static class CheckMigrations
	{
		
	}
	
	static class WorkNext
	{
		
	}
	*/
	
}
