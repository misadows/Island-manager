package Visualisation;

//import Island.EpochResult;

import Model.Topology;
import Topology.TopologySimulator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

    private Stage primaryStage;
    private BorderPane rootLayout;

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

            AnimationController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCharts(int island) {
        // 2. Charty bd jednak generowane dynamicznie
        // To można załatwić podobnie jak w przypadku innych komponentów (patrz komentarz do initComponent()
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Chart.fxml"));
            AnchorPane chart = (AnchorPane) loader.load();
            Stage chartStage = new Stage();
            chartStage.setTitle(CHAR_TITLE);
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
        // 4. Wyciągnać to gdzieś do innego layoutu
        // SRP. Nie jestem przekonany czy MainApp to dobre miejsce na zarządzanie symulacją.

        // Odpowiedzialnością MainApp jest stworzenie wszystkich kontrolerów i widoków
        // oraz pospinanie ich ze sobą. Zarządzaniem symulacją powinien się moim zdaniem
        // zająć jakiś główny kontroler (zwykle nazywa się go RootControllerem). Wówczas
        // każdy z kontrolerów miałby dostęp do RootControllera (a nie do MainApp) i przez
        // niego np. odpalał symulację.
        TopologySimulator topologySimulator = new TopologySimulator(topology);
        //Result result = topologySimulator.startSimulation();
        // TODO wait until Topology module finished, and connect all application parts
    }

    public static void main(String[] args) { launch(args); }

}