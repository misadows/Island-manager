package Island.Transformation;


import Island.Population;

public class SingleMutation implements TransformationInterface{
    private double rate;

    public SingleMutation(double rate){
        setRate(rate);
    }

    @Override
    public Population transform(Population population) {
        return population;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }
}
