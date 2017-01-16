package Visualisation;

import Island.IslandParams;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.util.regex.Pattern;


import java.util.List;

public class ConfigurationMenuController {

    private MainApp mainApp;

    private List<IslandParams> islands;
    public static final int TARGET_SOLUTION_LENGTH = 64;
    public static final String TS =" 1110000110101010100001110100110101111000101110101101010110111101";



    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton unidirectionalCircleRadioButton, eachOfEachRadioButton, ladderRadioButton, bidirectionalCircleRadioButton;

    @FXML
    private TextArea targetSolutionTextArea;

    @FXML
    public TabPane tabPane;

    @FXML
    private AnchorPane  islandsForm;



    private String targetSolution;
    private Alert alert = new Alert(Alert.AlertType.ERROR);



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
    private void initialize() {
        alert.setTitle("Error in form configuration");
        //Default configuration:


        bidirectionalCircleRadioButton.setToggleGroup(group);
        unidirectionalCircleRadioButton.setToggleGroup(group);
        eachOfEachRadioButton.setToggleGroup(group);
        ladderRadioButton.setToggleGroup(group);
        bidirectionalCircleRadioButton.setUserData(0);
        unidirectionalCircleRadioButton.setUserData(1);
        eachOfEachRadioButton.setUserData(2);
        ladderRadioButton.setUserData(3);


}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}