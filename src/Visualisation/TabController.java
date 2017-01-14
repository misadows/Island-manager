package Visualisation;

import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by GOSIA on 11.01.2017.
 */
public class TabController {

    ToggleGroup group = new ToggleGroup();

    @FXML
    private TextField population, tournament_size;
    @FXML
    private Slider basic_crossover,basic_mutation, single_point_mutation, single_point_crossover, migration;
    @FXML
    private CheckBox elitism;
    @FXML
    private void initialize(){
        elitism.setIndeterminate(false);

    }
    public TabController() {}
}
