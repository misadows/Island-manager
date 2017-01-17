package Island.Transformation;

import Island.Population;

public interface TransformationInterface {
    public Population transform(Population population);
    public void setRate(double rate);
}
