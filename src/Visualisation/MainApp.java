package Visualisation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Yoshimori on 05/12/2016.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

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

    public static void main(String[] args) { launch(args); }

}
