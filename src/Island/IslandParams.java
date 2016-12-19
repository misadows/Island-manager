package Island;


public class IslandParams {
    private int creaturesNumber;
    private double migrationRate;
    private double crossoverRate;
    private double mutationRate;
    private int generationsNumber;
    private int tournamentSize;
    private boolean elitism;
    private int genotypeSize=64;
    private String targetSolution;

    public IslandParams(int creaturesNumber, double migrationRate, double crossoverRate, double mutationRate,
                        int tournamentSize, boolean elitism, String targetSolution){
        this.creaturesNumber=creaturesNumber;
        this.migrationRate=migrationRate;
        this.crossoverRate=crossoverRate;
        this.mutationRate=mutationRate;
        this.tournamentSize=tournamentSize;
        this.elitism=elitism;
        this.targetSolution=targetSolution;
    }

    public String getTargetSolution() {
        return targetSolution;
    }

    public void setTargetSolution(String targetSolution) {
        this.targetSolution = targetSolution;
    }

    public int getGenotypeSize() {
        return genotypeSize;
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
