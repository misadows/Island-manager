package Visualisation;

//import Island.EpochResult;

import Model.Topology;
import Topology.TopologySimulator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    public TabPane tabPane;

    public MainApp() {}

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Island Manager");

        initRootLayout();
        //comparePath("ConfigurationMenu.fxml");
        //comparePath("Visualisation.fxml");
    }



    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));

            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);


            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void comparePath(String path)throws IOException {
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(path));
        AnchorPane component = (AnchorPane) loader.load();

        if(path.equals("ConfigurationMenu.fxml")){
            rootLayout.setRight(component);
        }
        else if(path.equals("Visualisation.fxml")){
            rootLayout.setCenter(component);
         }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        initComponent(path);

    }
    private void initComponent(String path)throws IOException {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(path));

            ConfigurationMenuController controllerCMC = loader.getController();
            controllerCMC.setMainApp(this);

            AnimationController controllerAC = loader.getController();
            controllerAC.setMainApp(this);
        for(Tab tab : tabPane.getTabs()){
            tab.setContent((AnchorPane) FXMLLoader.load(this.getClass().getResource("Tab.fxml")));
        }
    }


    public void showCharts(int island) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Chart.fxml"));
            AnchorPane chart = (AnchorPane) loader.load();
            Stage chartStage = new Stage();
            chartStage.setTitle("Islands Result");
            chartStage.initModality(Modality.WINDOW_MODAL);
            chartStage.initOwner(primaryStage);
            Scene scene = new Scene(chart, 704, 768);
            chartStage.setScene(scene);

//            TODO There is a bug connected with displaying charts, not repaired yet
//            ChartsController controller = loader.getController();
//            MockResults mockResults = new MockResults(100);
//            controller.setResultsData(mockResults, island);

            chartStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startProgram(Topology topology){
        TopologySimulator topologySimulator = new TopologySimulator(topology);
        //Result result = topologySimulator.startSimulation();
        // TODO wait until Topology module finished, and connect all application parts
    }

    public static void main(String[] args) { launch(args); }

}