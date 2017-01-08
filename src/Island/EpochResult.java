package Island;

import javax.persistence.*;

@Entity
@Table(name = "EpochResult")
public class EpochResult {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Transient
    private Population population;

    @Column(name = "maxFitness")
    private int maxFitness;

    @Column(name = "averageFitness")
    private double averageFitness;

    public Population getPopulation() {
        return population;
    }

    public int getMaxFitness() {
        return maxFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public EpochResult(Population population, FitnessCalculator fitnessCalculator){
        createResult(population, fitnessCalculator);
    }

    public void createResult(Population population, FitnessCalculator fitnessCalculator){
        this.maxFitness=fitnessCalculator.getFitness(population.getFittest());
        int fitnessSum = 0;
        for(int i=0; i<population.populationSize(); i++){
            fitnessSum += fitnessCalculator.getFitness(population.getCreature(i));
        }
        this.averageFitness = (double)fitnessSum/population.populationSize();
        this.population=population;

    }
}
