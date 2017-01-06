package Visualisation;

import Island.EpochResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Modality;


import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    ArrayList<EpochResult> ep;

    public MainApp() {
        //Initialization some sample data
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Island Manager");

        initRootLayout();

        showConfigurationMenu();
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

    private void showConfigurationMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ConfigurationMenu.fxml"));

            AnchorPane configurationMenu = (AnchorPane) loader.load();

            rootLayout.setCenter(configurationMenu);

            ConfigurationMenuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void showCharts() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("Charts.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("CHART");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the island into the controller
            ChartsController controller = loader.getController();
            controller.setResultsData(ep);

            dialogStage.show();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(args); }

}