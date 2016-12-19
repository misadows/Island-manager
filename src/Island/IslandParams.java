package Island;


public class IslandParams {
    private int creaturesNumber;
    private double migrationRate;
    private double crossoverRate;
    private double mutationRate;
    private int generationsNumber;
    private int tournamentSize;
    private boolean elitism;

    public IslandParams(int creaturesNumber, double migrationRate, double crossoverRate, double mutationRate, int tournamentSize, boolean elitism){
        this.creaturesNumber=creaturesNumber;
        this.migrationRate=migrationRate;
        this.crossoverRate=crossoverRate;
        this.mutationRate=mutationRate;
        this.tournamentSize=tournamentSize;
        this.elitism=elitism;
    }


    public boolean isElitism() {
        return elitism;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public int getCreaturesNumber() {
        return creaturesNumber;
    }

    public void setCreaturesNumber(int creaturesNumber) {
        this.creaturesNumber = creaturesNumber;
    }

    public double getMigrationRate() {
        return migrationRate;
    }

    public void setMigrationRate(double migrationRate) {
        this.migrationRate = migrationRate;
    }

    public int getGenerationsNumber() {
        return generationsNumber;
    }

    public void setGenerationsNumber(int generationsNumber) {
        this.generationsNumber = generationsNumber;
    }

}
