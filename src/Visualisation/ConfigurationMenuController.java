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
    public static String TARGET_SOLUTION = "1110000110101010100001110100110101111000101110101101010110111101";
    public static String INCORRECT_TARGET_SOLUTION = "Target solution: %s is incorrect! " +
            "\nThe length must be larger than 64 and please use only: (1 | 0)*";
    public static String ISLAND_PARSE_ERROR = "While parsing: %s in tab: %s\nError: %s";
    public static String TOPOLOGY_PARSE_ERROR = "While parsing: %s \nError: %s";
    public static String TAB_PATH = "Tab.fxml";
    public static String ERROR_MESSAGE_TITLE = "Error in form configuration";
    public static String INVALID_NODE_ID = "Invalid node id";
    public static String TOURNAMENT_SELECTION_ERROR = "Tournament selection must be between 1 and 1 000";
    public static String POPULATION_ERROR = "Population must be between 1 and 1 000 000";
    public static String TOPOLOGY_NOT_SELECTED = "Please select topology!";
    public static String GENERATIONS_ERROR = "Generations must be between 1 and 10 000";
    public static int[][][] TOPOLOGY = new int[][][]{
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
    //private TabController tabController;

    ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton unidirectionalCircleRadioButton;
    @FXML
    private RadioButton eachOfEachRadioButton;
    @FXML
    private RadioButton ladderRadioButton;
    @FXML
    private RadioButton bidirectionalCircleRadioButton;



    // 6. Rozbić konrolery i dorobić bindingi
    // Widzę tu zarówno zarządzanie przebiegiem symulacji, zarządzanie parametrami
    // wysp i zarządzanie topologiami. Wydzieliliście zarządzanie grafem i wykresami,
    // bo są niezależne od reszty. Tu pewnie będzie to trudniejsze, ale nikt nie powiedział,
    // że kontrolery muszą być niezależne - w szczególności można je ułożyć w hierarchię (drzewo).
    @FXML
    private TextField generationsTextField;

    @FXML
    private TextArea targetSolutionTextArea;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane topologyForm, islandsForm;

    // TO DELETE IN TAB CONTROLLER
    private int startingPopulation, tournamentSize;
    private double basicCrossover, basicMutation, singlePointCrossover, singlePointMutation, migrationRate;
    private boolean elitism;
    private String targetSolution;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    // END
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
            if(generations < 1 || generations > 10000) throw new FormException(GENERATIONS_ERROR);
            if(group.getSelectedToggle() == null) throw new FormException(TOPOLOGY_NOT_SELECTED);
            setTopology(new Topology(this.islands, TOPOLOGY[(Integer)group.getSelectedToggle().getUserData()], generations));
        }
        catch(Exception e){
            showErrorDialog(String.format(TOPOLOGY_PARSE_ERROR, generationsTextField.getId(), e.getMessage()));
            return false;
        }
        return true;
    }

    private boolean addIslandParams(AnchorPane ap, String tab) {
        for(Node node : ap.getChildren()) {
            if(node.getId() == null) continue;
            try{
                // 10. Zdecydowanie nie po to ludzie od JavaFX tak się namęczyli z klepaniem bindingów i observerów
                // realizowanych jako Property żebyśmy musieli teraz ifować kontrolki... Kilka rzeczy do poprawy:

                // I. Kontroler nie musi wiedzieć nic o ograniczeniach konkretnych parametrów, to jest część
                // modelu i powinna się znaleźć w modelu (cała walidacja moim zdaniem).

                // II. W modelu parametrów powinny się znaleźć Property (dla każdego parametru jedno),
                // które można zbindować z odpowiednimi property konkretnych kontrolek. Ilekroć zmieni się
                // wartość w GUI, automatycznie ustawi się również wartość w modelu.
                switch(node.getId()){
                    case "population":
                        startingPopulation = Integer.parseInt(((TextField) node).getText());
                        if(startingPopulation < 1 || startingPopulation >= 1000000) throw new FormException(POPULATION_ERROR);
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
                        if(tournamentSize < 0 || tournamentSize >= 1000) throw new FormException(TOURNAMENT_SELECTION_ERROR);
                        break;
                    case "elitism":
                        elitism = ((CheckBox) node).isSelected();
                        break;

                    default:
                        System.out.println(INVALID_NODE_ID);

                }
            }
            catch(Exception e){
                showErrorDialog(String.format(ISLAND_PARSE_ERROR, node.getId(), tab, e.getMessage()));
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

    private void showErrorDialog(String message) {
        alert.setContentText(message);
        alert.showAndWait();
        disableForms(false);
    }

    private boolean validateTargetSolution() {
        targetSolution = targetSolutionTextArea.getText();
        if(targetSolution.isEmpty()) {
            targetSolution = TARGET_SOLUTION;
        }
        else if(!Pattern.matches("[01]*", targetSolution) || targetSolution.length() < 64){
            showErrorDialog(String.format(INCORRECT_TARGET_SOLUTION, targetSolution));
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
        //tabController = new TabController();
        //tabController.setConfigurationMenuController(this);
        alert.setTitle(ERROR_MESSAGE_TITLE);
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
            tab.setContent((AnchorPane) FXMLLoader.load(this.getClass().getResource(TAB_PATH)));
        }
}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}