package Topology;


import java.util.ArrayList;
import java.util.List;

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
	

}
