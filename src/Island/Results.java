package Island;

import javax.persistence.*;
import Model.Result;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Results")
public class Results {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EpochResult> epochResults = new ArrayList<EpochResult>();

    public Results() {
    }

    public List<EpochResult> getEpochResults() {
        return epochResults;
    }

    public void addResult(Population population, FitnessCalculator fitnessCalculator){
        epochResults.add(new EpochResult(population, fitnessCalculator));
    }

    public int getId(){
        return id;
    }

}
