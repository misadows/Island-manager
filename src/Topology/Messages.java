package Topology;

import Model.Island;

public class Messages
{
	//creating the messages
	static class StartSimulation
	{
		
	}
	
	static class Work
	{
		/*
		 parameters
		 */
		private final int generations;
		
		public Work(int generations)
		{
			this.generations = generations;
		}
		
		public int getGenerations()
		{
			return generations;
		}
	}
	
	
	static class SetWorker
	{
		private final int id;
		private final Island island;

		public SetWorker(int id, Island island)
		{
			this.id = id;
			this.island = island;
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
	
	
}
