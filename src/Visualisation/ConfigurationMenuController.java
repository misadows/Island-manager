package Visualisation;

import Island.IslandParams;
<<<<<<< HEAD
import Model.Topology;
=======
import Topology.TopologyParams;
>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
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
    boolean a = true; // flaga
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
<<<<<<< HEAD
        disableForms(true);
        if (!validateTargetSolution()) return;
        islands = new ArrayList<>();
        for(Tab tab : tabPane.getTabs()){
            if(!addIslandParams((AnchorPane) tab.getContent(), tab.getText())) return;
=======
        minTextFieldValidation();
        maxTextFieldValidation();
        TextField [] tab = {startingPopulation1TextField, startingPopulation2TextField,startingPopulation3TextField,startingPopulation4TextField,
                tournamentSize1TextField, tournamentSize2TextField, tournamentSize3TextField, tournamentSize4TextField};

        for(int i=0; i<tab.length; i++){
            if ( ! (tab[i].getText().length()>=1 && Character.isDigit(tab[i].getText().charAt(0)))) {
                System.out.println("Podana liczba musi nalezec do liczb naturalnych !");
                a = false;
            }
        }
        if(a) {
            setParams();
>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
        }
        if(!addTopologyParams()) return;
        this.mainApp.startProgram(getTopology());
    }

<<<<<<< HEAD
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
=======
    }

    private void setParams() {
        setIsland(Integer.parseInt(startingPopulation1TextField.getText()),
                basicCrossover1Slider.getValue(),
                basicMutation1Slider.getValue(),
                singlePointCrossover1Slider.getValue(),
                singlePointMutation1Slider.getValue(),
                migrationRate1Slider.getValue(),
                Integer.parseInt(tournamentSize1TextField.getText()),
                elitism1CheckBox.isSelected(), "Funkcja"
        );

        setIsland(Integer.parseInt(startingPopulation2TextField.getText()),
                basicCrossover2Slider.getValue(),
                basicMutation2Slider.getValue(),
                singlePointCrossover2Slider.getValue(),
                singlePointMutation2Slider.getValue(),
                migrationRate2Slider.getValue(),
                Integer.parseInt(tournamentSize2TextField.getText()),
                elitism2CheckBox.isSelected(), "Funkcja"
        );

        setIsland(Integer.parseInt(startingPopulation3TextField.getText()),
                basicCrossover3Slider.getValue(),
                basicMutation3Slider.getValue(),
                singlePointCrossover3Slider.getValue(),
                singlePointMutation3Slider.getValue(),
                migrationRate3Slider.getValue(),
                Integer.parseInt(tournamentSize3TextField.getText()),
                elitism3CheckBox.isSelected(), "Funkcja"
        );

        setIsland(Integer.parseInt(startingPopulation4TextField.getText()),
                basicCrossover4Slider.getValue(),
                basicMutation4Slider.getValue(),
                singlePointCrossover4Slider.getValue(),
                singlePointMutation4Slider.getValue(),
                migrationRate4Slider.getValue(),
                Integer.parseInt(tournamentSize4TextField.getText()),
                elitism4CheckBox.isSelected(), "Funkcja"

        );

        setTopology(islands, TOPOLOGY[(Integer)group.getSelectedToggle().getUserData()],
                Integer.parseInt(generationsTextField.getText()));

>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
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

<<<<<<< HEAD
    private boolean validateTargetSolution() {
        targetSolution = targetSolutionTextArea.getText();
        if(targetSolution.isEmpty()) targetSolution = "1110000110101010100001110100110101111000101110101101010110111101";
        else if(!Pattern.matches("[01]*", targetSolution) || targetSolution.length() < 64){
            printError("Target solution: " + targetSolution + " is incorrect! " +
                    "\nThe length must be larger than 64 and please use only: (1 | 0)*");
        }
        return true;
=======
    private void minTextFieldValidation(){
        //checking if it's natural number
        if ( (minimumDomainTextField.getText().length()>=1 && Character.isDigit(minimumDomainTextField.getText().charAt(0))))
            Integer.parseInt(minimumDomainTextField.getText());
        else
            System.out.println("Podana liczba musi nalezec do liczb naturalnych !");
            a = false;


    }

    private void maxTextFieldValidation(){
        //checking if it is natural number and > than minimum
        if ( (maximumDomainTextField.getText().length()>=1 && Character.isDigit(maximumDomainTextField.getText().charAt(0)))&&Integer.parseInt(maximumDomainTextField.getText())>Integer.parseInt(minimumDomainTextField.getText()))
            Integer.parseInt(maximumDomainTextField.getText());
        else {
            System.out.println("Podana liczba musi nalezec do liczb naturalnych i być wieksza od minimum!");
            a = false;
        }


>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
    }

    @FXML
    private void handleCancelButtonAction() {
        disableForms(false);
    }

    public Topology getTopology(){
        return this.topology;
    }

<<<<<<< HEAD
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

=======
    @FXML
    private void initialize() {
>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
    bidirectionalCircleRadioButton.setToggleGroup(group);
    unidirectionalCircleRadioButton.setToggleGroup(group);
    eachOfEachRadioButton.setToggleGroup(group);
    ladderRadioButton.setToggleGroup(group);
<<<<<<< HEAD
    bidirectionalCircleRadioButton.setUserData(0);
    unidirectionalCircleRadioButton.setUserData(1);
    eachOfEachRadioButton.setUserData(2);
    ladderRadioButton.setUserData(3);

    for(Tab tab : tabPane.getTabs()){
        tab.setContent((AnchorPane) FXMLLoader.load(this.getClass().getResource("Tab.fxml")));
    }
=======
    bidirectionalCircleRadioButton.setUserData(1);
    unidirectionalCircleRadioButton.setUserData(2);
    eachOfEachRadioButton.setUserData(3);
    ladderRadioButton.setUserData(4);
   // islands = new ArrayList<IslandParams>();


>>>>>>> bb49f7ef38b9e3f7673dc67d8add1dee2d661cc9
}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}