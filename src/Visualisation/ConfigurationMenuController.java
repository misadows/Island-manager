package Visualisation;

import Island.IslandParams;
import Model.Topology;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ConfigurationMenuController {
    int[][][] TOPOLOGY = new int[][][]{
            {
                    {0, 1, 1, 1},
                    {1, 0, 1, 1},
                    {1, 1, 0, 1},
                    {1, 1, 1, 0}
            },
            {
                    {0, 1, 0, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1},
                    {1, 0, 0, 0}
            },
            {
                    {0, 1, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 1},
                    {0, 1, 1, 0}
            },
            {
                    {0, 1, 1, 0},
                    {1, 0, 0, 1},
                    {1, 0, 0, 1},
                    {0, 1, 1, 0}
            }
    };
    private MainApp mainApp;

    private Topology topology;
    private List<IslandParams> islands;

    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton unidirectionalCircleRadioButton, eachOfEachRadioButton, ladderRadioButton, bidirectionalCircleRadioButton;

    @FXML
    private TextField generationsTextField;

    @FXML
    private TextArea targetSolutionTextArea;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane topologyForm, islandsForm;


    private int startingPopulation, tournamentSize;
    private double basicCrossover, basicMutation, singlePointCrossover, singlePointMutation, migrationRate;
    private boolean elitism;
    private String targetSolution;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private void handleStartButtonAction() {
        disableForms(true);
        if (!validateTargetSolution()) return;
        islands = new ArrayList<>();
        for(Tab tab : tabPane.getTabs()){
            if(!addIslandParams((AnchorPane) tab.getContent(), tab.getText())) return;
        }
        if(!addTopologyParams()) return;
        this.mainApp.startProgram(getTopology());
    }

    private boolean addTopologyParams() {
        try{
            int generations = Integer.parseInt(generationsTextField.getText());
            if(generations < 1 || generations > 10000) throw new FormException("Generations must be between 1 and 10 000");
            if(group.getSelectedToggle() == null) throw new FormException("Please select topology!");
            setTopology(new Topology(this.islands, TOPOLOGY[(Integer)group.getSelectedToggle().getUserData()], generations));
        }
        catch(Exception e){
            printError("While parsing: " + generationsTextField.getId() + "\nError:" + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean addIslandParams(AnchorPane ap, String tab) {
        for(Node node : ap.getChildren()) {
            if(node.getId() == null) continue;
            try{
                switch(node.getId()){
                    case "population":
                        startingPopulation = Integer.parseInt(((TextField) node).getText());
                        if(startingPopulation < 1 || startingPopulation >= 1000000) throw new FormException("Population must be between 1 and 1 000 000");
                        break;
                    case "basic_crossover":
                        basicCrossover = ((Slider) node).getValue();
                        break;
                    case "basic_mutation":
                        basicMutation = ((Slider) node).getValue();
                        break;
                    case "single_point_mutation":
                        singlePointMutation = ((Slider) node).getValue();
                        break;
                    case "single_point_crossover":
                        singlePointCrossover = ((Slider) node).getValue();
                        break;
                    case "migration":
                        migrationRate = ((Slider) node).getValue();
                        break;
                    case "tournament_size":
                        tournamentSize = Integer.parseInt(((TextField) node).getText());
                        if(tournamentSize < 0 || tournamentSize >= 1000) throw new FormException("Tournament selection must be between 1 and 1 000");
                        break;
                    case "elitism":
                        elitism = ((CheckBox) node).isSelected();
                        break;

                    default:
                        System.out.println("Invalid node id");

                }
            }
            catch(Exception e){
                printError("While parsing: " + node.getId() + "in tab:"+ tab + "\nError:" + e.getMessage());
                return false;
            }
        }
        this.islands.add(new IslandParams(startingPopulation, migrationRate, basicCrossover, basicMutation,
                singlePointCrossover, singlePointMutation, tournamentSize, elitism, targetSolution));
        return true;
    }

    private void disableForms(boolean form) {
        topologyForm.setDisable(form);
        islandsForm.setDisable(form);
    }

    private void printError(String message) {
        System.out.println(message);
        alert.setContentText(message);
        alert.showAndWait();
        disableForms(false);
    }

    private boolean validateTargetSolution() {
        targetSolution = targetSolutionTextArea.getText();
        if(targetSolution.isEmpty()) targetSolution = "1110000110101010100001110100110101111000101110101101010110111101";
        else if(!Pattern.matches("[01]*", targetSolution) || targetSolution.length() < 64){
            printError("Target solution: " + targetSolution + " is incorrect! " +
                    "\nThe length must be larger than 64 and please use only: (1 | 0)*");
        }
        return true;
    }

    @FXML
    private void handleCancelButtonAction() {
        disableForms(false);
    }

    public Topology getTopology(){
        return this.topology;
    }

    public void setTopology(Topology topology) {
        this.topology = topology;
    }

    public ConfigurationMenuController() {}

    @FXML
    private void initialize() throws IOException {
    alert.setTitle("Error in form configuration");
    //Default configuration:
    startingPopulation = 1000;
    tournamentSize = 10;
    basicCrossover = 0.1;
    basicMutation = 0.1;
    singlePointCrossover = 0.1;
    singlePointMutation = 0.1;
    migrationRate = 0.1;
    elitism = true;

    bidirectionalCircleRadioButton.setToggleGroup(group);
    unidirectionalCircleRadioButton.setToggleGroup(group);
    eachOfEachRadioButton.setToggleGroup(group);
    ladderRadioButton.setToggleGroup(group);
    bidirectionalCircleRadioButton.setUserData(0);
    unidirectionalCircleRadioButton.setUserData(1);
    eachOfEachRadioButton.setUserData(2);
    ladderRadioButton.setUserData(3);

    for(Tab tab : tabPane.getTabs()){
        tab.setContent((AnchorPane) FXMLLoader.load(this.getClass().getResource("Tab.fxml")));
    }
}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}