package Island;


public class IslandParams {
    private int creaturesNumber;

    private double migrationRate;
    private double basicCrossoverRate;
    private double basicMutationRate;
    private double singlePointCrossoverRate;
    private double singleMutationRate;

    private int generationsNumber;
    private int tournamentSize;
    private boolean elitism;

    private int genotypeSize=64;
    private String targetSolution;

    public IslandParams(int creaturesNumber, double migrationRate, double basicCrossoverRate,
                        double basicMutationRate, double singlePointCrossoverRate, double singleMutationRate,
                        int tournamentSize, boolean elitism, String targetSolution){
        this.creaturesNumber=creaturesNumber;
        this.migrationRate=migrationRate;
        this.basicCrossoverRate = basicCrossoverRate;
        this.basicMutationRate = basicMutationRate;
        this.singlePointCrossoverRate = singlePointCrossoverRate;
        this.singleMutationRate = singleMutationRate;
        this.tournamentSize=tournamentSize;
        this.elitism=elitism;
        this.targetSolution=targetSolution;
    }

    public double getSinglePointCrossoverRate() {
        return singlePointCrossoverRate;
    }

    public double getSingleMutationRate() {
        return singleMutationRate;
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

    public double getBasicCrossoverRate() {
        return basicCrossoverRate;
    }

    public double getBasicMutationRate() {
        return basicMutationRate;
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
