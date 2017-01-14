package Visualisation;

import Island.IslandParams;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ConfigurationMenuController {

    private MainApp mainApp;

    private List<IslandParams> islands;
    public static final String TS =" 1110000110101010100001110100110101111000101110101101010110111101";
    public static final int TARGET_SOLUTION_LENGTH = 64;
    public static final int TOURNAMENT_MIN_SIZE = 1;
    public static final int TOURNAMENT_MAX_SIZE = 1000;
    public static final int MIN_STARTING_POPULATION = 1;
    public static final int MAX_STARTING_POPULATION = 1000000;

    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton unidirectionalCircleRadioButton, eachOfEachRadioButton, ladderRadioButton, bidirectionalCircleRadioButton;

    @FXML
    private TextArea targetSolutionTextArea;

    @FXML
    public TabPane tabPane;

    @FXML
    private AnchorPane  islandsForm;


    private int startingPopulation, tournamentSize;
    private double basicCrossover, basicMutation, singlePointCrossover, singlePointMutation, migrationRate;
    private boolean elitism;
    private String targetSolution;
    private Alert alert = new Alert(Alert.AlertType.ERROR);


    public boolean addIslandParams(AnchorPane ap, String tab) {
        for(Node node : ap.getChildren()) {
            if(node.getId() == null){
                continue;
            }
            try{
                switch(node.getId()){
                    case "population":
                        startingPopulation = Integer.parseInt(((TextField) node).getText());
                        if(startingPopulation < MIN_STARTING_POPULATION || startingPopulation > MAX_STARTING_POPULATION) {
                            throw new FormException("Population must be between"+MIN_STARTING_POPULATION+" and " + MAX_STARTING_POPULATION);
                        }
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
                        if(tournamentSize < TOURNAMENT_MIN_SIZE || tournamentSize > TOURNAMENT_MAX_SIZE){
                            throw new FormException("Tournament selection must be between" + TOURNAMENT_MIN_SIZE + " and " + TOURNAMENT_MAX_SIZE);
                        }
                        break;
                    case "elitism":
                        elitism = ((CheckBox) node).isSelected();
                        break;

                    default:
                        System.out.println("Invalid node id");

                }
            }
            catch(Exception e){
                showErrorDialog("While parsing: " + node.getId() + "in tab:"+ tab + "\nError:" + e.getMessage());
                return false;
            }
        }
        this.islands.add(new IslandParams(startingPopulation, migrationRate, basicCrossover, basicMutation,
                singlePointCrossover, singlePointMutation, tournamentSize, elitism, targetSolution));
        return true;
    }

    private void disableForms(boolean form) {
        islandsForm.setDisable(form);
    }

    public void showErrorDialog(String message) {
        alert.setContentText(message);
        alert.showAndWait();
        disableForms(false);
    }

    public boolean validateTargetSolution() {
        targetSolution = targetSolutionTextArea.getText();
        if(targetSolution.isEmpty()) {
            targetSolution = TS;
        }
        else if(!Pattern.matches("[01]*", targetSolution) || targetSolution.length() < TARGET_SOLUTION_LENGTH){
            showErrorDialog("Target solution: " + targetSolution + " is incorrect! " +
                    "\nThe length must be larger than "+ TARGET_SOLUTION_LENGTH + " and please use only: (1 | 0)*");
        }
        return true;
    }

    @FXML
    private void handleCancelButtonAction() {
        disableForms(false);
    }


    public ConfigurationMenuController(List<IslandParams> islands) {
        this.islands=islands;
    }
    public ConfigurationMenuController() {

    }

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