package Visualisation;

import Island.IslandParams;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

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
    private TopologyParams topology;
    private List<IslandParams> islands;

    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton unidirectionalCircleRadioButton, eachOfEachRadioButton, ladderRadioButton, bidirectionalCircleRadioButton;

    @FXML
    private TextField generationsTextField;

    @FXML
    private SplitMenuButton functionsSplitMenuButton;

    @FXML
    private TextField minimumDomainTextField, maximumDomainTextField;

    @FXML
    private TextField startingPopulation1TextField, startingPopulation2TextField,
            startingPopulation3TextField, startingPopulation4TextField;

    @FXML
    private Slider basicCrossover1Slider, basicCrossover2Slider,
            basicCrossover3Slider, basicCrossover4Slider;

    @FXML
    private Slider basicMutation1Slider, basicMutation2Slider,
            basicMutation3Slider, basicMutation4Slider;

    @FXML
    private Slider singlePointCrossover1Slider, singlePointCrossover2Slider,
            singlePointCrossover3Slider, singlePointCrossover4Slider;

    @FXML
    private Slider singlePointMutation1Slider, singlePointMutation2Slider,
            singlePointMutation3Slider,singlePointMutation4Slider;

    @FXML
    private Slider migrationRate1Slider, migrationRate2Slider,
            migrationRate3Slider, migrationRate4Slider;

    @FXML
    private TextField tournamentSize1TextField, tournamentSize2TextField,
            tournamentSize3TextField, tournamentSize4TextField;

    @FXML
    private CheckBox elitism1CheckBox, elitism2CheckBox,
            elitism3CheckBox, elitism4CheckBox;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button startButton, cancelButton;

    @FXML
    private void handleStartButtonAction() {
        if(validate_configuration()) {
            setParams();
        }

        // TO DO
        // Get all data from the form +
        // Validate inputs - make tests
        // What if user don't input all data, set default or show error?!
        // Make functionSplitMenu working
        // Implement "targetSolution -> pass function -> Make functionSplitMenu working
        // Freeze configuration
        // Refactor field names
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
    }

    public void setTopology(List<IslandParams> islands, int[][] connections, int generations) {
        this.topology = new TopologyParams(islands, connections, generations);
    }

    public void setIsland(int creaturesNumber, double migrationRate, double basicCrossoverRate, double basicMigrationRate,
                          double singlePointCrossoverRate, double singleMutationRate, int tournamentSize,
                          boolean elitism, String targetSolution) {
        this.islands.add(new IslandParams(creaturesNumber, migrationRate, basicCrossoverRate, basicMigrationRate,
                singlePointCrossoverRate, singleMutationRate, tournamentSize, elitism, targetSolution));
    }

    private boolean validate_configuration() {
        System.out.println("It would validate configuration form ...");
        return true;
    }

    @FXML
    private void handleCancelButtonAction() {
        System.out.println("Button was clicked, stop action ...");

    }


    public ConfigurationMenuController() {

    }

    @FXML
    private void initialize() {
        bidirectionalCircleRadioButton.setToggleGroup(group);
        unidirectionalCircleRadioButton.setToggleGroup(group);
        eachOfEachRadioButton.setToggleGroup(group);
        ladderRadioButton.setToggleGroup(group);
        bidirectionalCircleRadioButton.setUserData(1);
        unidirectionalCircleRadioButton.setUserData(2);
        eachOfEachRadioButton.setUserData(3);
        ladderRadioButton.setUserData(4);
        islands = new ArrayList<IslandParams>();


        //TO DO
        // Ask about default min and max for this values and set sliders




    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}