package Island;


public class IslandParams {
    private int creaturesNumber;
    private double migrationRate;
    private double mutationProbability;
    private int generationsNumber;


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

    public double getMutationProbability() {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public int getGenerationsNumber() {
        return generationsNumber;
    }

    public void setGenerationsNumber(int generationsNumber) {
        this.generationsNumber = generationsNumber;
    }

}
