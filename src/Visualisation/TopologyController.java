package Visualisation;

import Island.IslandParams;
import Model.Topology;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GOSIA on 12.01.2017.
 */
public class TopologyController extends TabController {

    private List<IslandParams> isl;
    private ConfigurationMenuController cmc = new ConfigurationMenuController(isl);
    private MainApp mainApp;
    public static final int MIN_GENERATION = 1;
    public static final int MAX_GENERATION = 10000;

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
    private Topology topology;
    @FXML
    private TextField generationsTextField;
    @FXML
    private AnchorPane topologyForm;
    @FXML
    private void handleStartButtonAction() {
        disableForms(true);
        if (!validateTargetSolution()) {
            return;
        }
        isl = new ArrayList<>();
        for(Tab tab : tabPane.getTabs()){
            if(!addIslandParams((AnchorPane) tab.getContent(), tab.getText())){
                return;
            }
        }
        if(!addTopologyParams()) {
            return;
        }
        this.mainApp.startProgram(getTopology());
    }
    private boolean addTopologyParams() {
        try{
            int generations = Integer.parseInt(generationsTextField.getText());
            if(generations < MIN_GENERATION || generations > MAX_GENERATION) {
                    throw new FormException("Generations must be between"+ MIN_GENERATION+" and" + MAX_GENERATION);
            }

            if(group.getSelectedToggle() == null) {
                throw new FormException("Please select topology!");
            }
           //setTopology(new Topology(this.islands, TOPOLOGY[(Integer)group.getSelectedToggle().getUserData()], generations));

        }
        catch(Exception e){
            showErrorDialog("While parsing: " + generationsTextField.getId() + "\nError:" + e.getMessage());
            return false;
        }
        return true;
    }
    private void disableForms(boolean form) {
        topologyForm.setDisable(form);
    }
    public Topology getTopology(){
        return this.topology;
    }

    public void setTopology(Topology topology) {
        this.topology = topology;
    }
    public TopologyController() {

   }

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
