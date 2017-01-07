package Visualisation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Arrays;
import java.util.List;


public class ChartsController {
    private MainApp mainApp;

    @FXML
    private LineChart<Integer, Integer> lineChart;

    @FXML
    private CategoryAxis generationsAxis;

    private ObservableList<String> generations = FXCollections.observableArrayList();

    public void setResultsData(MockResults results, int island) {
        List<Integer> result = results.getIslandResults()[island];

        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < result.size(); i++) {
            series.getData().add(new XYChart.Data(i, result.get(i)));
        }
        //It will be possible to add exactly 3 series averageFitness, maximumFitness, populationNumber
        lineChart.getData().add(series);
    }

public ChartsController() {}

    @FXML
    private void initialize() {
        // Temporary added, generationAxis will be dynamically change depend of generation size
        generations.addAll(Arrays.asList("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"));
        generationsAxis.setCategories(generations);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
