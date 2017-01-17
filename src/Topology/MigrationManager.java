package Topology;


import java.util.ArrayList;
import java.util.List;

import Island.Creature;

public class MigrationManager
{
	private List<Migration> migrations;
	
	public MigrationManager(){
		migrations = new ArrayList<Migration>();	
	}
	
	public void addMigration(Migration migration)
	{
		migrations.add(migration);
	}
	
	 public void removeMigration(Migration migration)
	    {
	    	migrations.remove(migration);
	    }
	
	public List<Migration> getMigrations()
	{
		return migrations;
	}
	
	//jak na razie migrations przechowuje pojedyncze osobniki. Metoda laczy wszystkie migracje pojedynczych osobinkow w jedna migracje
	//laczy migracje, gdy dwie migracje odbyly sie z tej tej samej wyspy do tej samej wyspy, oraz wystapily w tej samej generacji
	public void concatenateMigration()
	{
		List<Migration> migrations = new ArrayList<Migration>();
		int currentGeneration;
		int currentFrom;
		int currentWhere;
		int currentSize = this.migrations.size();
		boolean first;
		for(int i=0;i<currentSize;i++)
		{
			currentGeneration = this.migrations.get(i).getGeneration();
			currentFrom = this.migrations.get(i).getFrom();
			currentWhere = this.migrations.get(i).getWhere();
			first = true;
			for(int j=0;j<currentSize;j++)
			{
				if(currentGeneration == this.migrations.get(j).getGeneration())
					if(currentFrom == this.migrations.get(j).getFrom())
						if(currentWhere == this.migrations.get(j).getWhere())
						{
							if(first)
							{
								migrations.add(this.migrations.get(j));
								first = false;
							}
							else 
							{
								migrations.get(migrations.size()-1).addCreature(this.migrations.get(j).getMigration().get(0)); //migracja ma tylko jednego osobnika
							}
							this.migrations.remove(j);
							currentSize = this.migrations.size();
						}
			}
			
		}
		this.migrations.addAll(migrations);
	}
	

}
