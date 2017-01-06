package Visualisation;

import javafx.fxml.FXML;
public class RootLayoutController {

    private MainApp mainApp;
    /**
     * Opens chart.
     */
    //clicking on island will cause that chart of results will be shown
    @FXML
    private void handleShowCharts() {
        mainApp.showCharts();
    }
    public RootLayoutController() {

    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
