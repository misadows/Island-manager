package Visualisation;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;


public class ChartsController {


    private LineChart lineChart;
    private XYChart.Series series;

    public Group setResultsData(MockResults results, int island) {
        List<Integer> result = results.getIslandResults()[island];

        XYChart.Series max_population = new XYChart.Series();
        XYChart.Series max_fitness = new XYChart.Series();
        XYChart.Series average_fitness = new XYChart.Series();
        max_population.setName("Max population");
        max_fitness.setName("Max fitness");
        average_fitness.setName("Average fitness");
        for (int i = 0; i < (result.size()-5)/3; i+=5) {
            max_population.getData().add(new XYChart.Data(i, result.get(i)));
            max_fitness.getData().add(new XYChart.Data(i, result.get(i+result.size()/3)));
            average_fitness.getData().add(new XYChart.Data(i, result.get(i+(result.size()/3))*2));
        }
        //It will be possible to add exactly 3 series averageFitness, maximumFitness, populationNumber
        lineChart.getData().add(max_population);
        lineChart.getData().add(max_fitness);
        lineChart.getData().add(average_fitness);
        Group root = new Group(lineChart);
        return root;
    }

    public ChartsController(int generations) {
        //Defining the x axis
        NumberAxis xAxis = new NumberAxis(0, generations, 10);
        xAxis.setLabel("Generations");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        //Creating the line chart
        lineChart = new LineChart(xAxis, yAxis);
    }
}
