package Visualisation;


import Model.Result;
import Model.Topology;
import Topology.TopologySimulator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static String TITLE = "Island Manager";
    public static String CHAR_TITLE = "Islands Result";
    public static String ROOT_LAYOUT = "RootLayout.fxml";
    public static String CONFIGURATION_MENU = "ConfigurationMenu.fxml";
    public static String VISUALISATION = "Visualisation.fxml";

    private Result result;
    private MockResults mockResults;

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnimationController animationController;

    public MainApp() {}

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(TITLE);

        initRootLayout();
        initConfigurationMenu();
        initVisualisation();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ROOT_LAYOUT));

            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);


            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initConfigurationMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(CONFIGURATION_MENU));

            AnchorPane configurationMenu = (AnchorPane) loader.load();

            rootLayout.setRight(configurationMenu);

            ConfigurationMenuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initVisualisation() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(VISUALISATION));

            AnchorPane configurationMenu = (AnchorPane) loader.load();

            rootLayout.setCenter(configurationMenu);

            animationController = loader.getController();
            animationController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCharts(int island) {
        Stage chartStage = new Stage();
        chartStage.setTitle(CHAR_TITLE);
        chartStage.initModality(Modality.WINDOW_MODAL);
        chartStage.initOwner(primaryStage);

        ChartsController controller = new ChartsController(100);

        Group root = controller.setResultsData(mockResults, island);

        //Creating a scene object
        Scene scene = new Scene(root, 500, 400);

        //Setting title to the Stage
        chartStage.setTitle("Island " + (island+1));

        //Adding scene to the stage
        chartStage.setScene(scene);
        chartStage.sizeToScene();
        //Displaying the contents of the stage
        chartStage.show();
    }

    public void startProgram(Topology topology){
        TopologySimulator topologySimulator = new TopologySimulator(topology);
        // result = topologySimulator.startSimulation();
        // if(result != 0) animationController.startAnimation();
        mockResults = new MockResults(100);
        animationController.startAnimation(mockResults.getMigrations(), mockResults.getGenerations());
        // TODO wait until Topology module finished, and connect all application parts
    }

    public static void main(String[] args) { launch(args); }

}