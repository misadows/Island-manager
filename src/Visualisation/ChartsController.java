package Visualisation;

/**
 *  We got island collection and according to task we have to create chart for chosen island
 */
import Island.*;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Arrays;


public class ChartsController {


    //setting up outcomes from the island (taking under notice Results class and EpochResult class)
    private IslandParams params;
    private Population population;
    private FitnessCalculator fitnessCalculator;
    private EpochResult ep = new EpochResult(population, fitnessCalculator);
    private Island island = new Island(params);

    @FXML
    private TextField islandNameTextField;
    @FXML
    private TextField ichartTitleTextField;
    @FXML
    private CategoryAxis chartCategoryAxis;
    @FXML
    private NumberAxis chartNumberAxis;
    private ObservableList<String> mn = FXCollections.observableArrayList();

    public ChartsController() {

    }

    @FXML
    private void initialize() {

        //and here I'm little bit confused that we should make 3D diagram and on OZ set 'epochResult' or just make 2D diagram ?!
        island.getResults(); // the list of the results
        ep.createResult(population, fitnessCalculator); // the list of the epoch results
        mn.addAll(Arrays.asList(island.getResults().toString()));
        chartCategoryAxis.setCategories(mn);

    }
    public void setResultsData(ArrayList<EpochResult> ep) {
       // to implement
    }




}
