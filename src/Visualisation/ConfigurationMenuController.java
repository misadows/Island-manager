package Visualisation;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;




public  class ConfigurationMenuController {
    private MainApp mainApp;

    /*** AUTOMATIC INJECTION ***/

    @FXML
    private ResourceBundle resources;

    //labels
    @FXML
    private Label topologyLabel, functionLabel, minimumLabel, maximumLabel, startingPoulation1Label, startingPoulation2Label,startingPoulation3Label,startingPoulation4Label,  crossover1Label, crossover2Label, crossover3Label, crossover4Label, mutation1Label, mutation2Label, mutation3Label, mutation4Label, generations1Label, generations2Label, generations3Label, generations4Label, tournament1SelectionLabel, tournament2SelectionLabel, tournament3SelectionLabel, tournament4SelectionLabel, migrationInterval1Label, migrationInterval2Label, migrationInterval3Label, migrationInterval4Label, migrationRate1Label, migrationRate2Label, migrationRate3Label, migrationRate4Label;


    //radio buttons
    @FXML
    private RadioButton unidirectionalCircleRadioButton, eachOfEachRadioButton, ladderRadioButton, bidirectionalCircleRadioButton;

    //imageViews
    @FXML
    private ImageView eachOfEachImageView, bidirectionalCircleImageView, ladderImageView, unidirectionalCircleImageView;

    //split menu button
    @FXML
    private SplitMenuButton functionsSplitMenuButton;

    //tabs
    @FXML
    private Tab islandITab, islandIITab, islandIIITab, islandIVTab;

    //text fields
    @FXML
    private TextField minimumTextField, maximumTextField, startingPopulation1TextField, startingPopulation2TextField, startingPopulation3TextField, startingPopulation4TextField, generations1TextField, generations2TextField, generations3TextField, generations4TextField, migrationRateText1Field, migrationRateText2Field, migrationRateText3Field, migrationRateText4Field;

    //text areas
    @FXML
    private TextArea minimumTextArea, maximumTextArea, startingPopulation1TextArea, startingPopulation2TextArea, startingPopulation3TextArea, startingPopulation4TextArea, generations1TextArea, generations2TextArea, generations3TextArea, generations4TextArea, migrationRate1TextArea, migrationRate2TextArea, migrationRate3TextArea, migrationRate4TextArea ;
    @FXML
    private TextArea outputTextArea;

    //sliders
    @FXML
    private Slider mutation1Slider, mutation2Slider, mutation3Slider, mutation4Slider, tournament1SelectionSlider, tournament2SelectionSlider, tournament3SelectionSlider, tournament4SelectionSlider, migrationInterval1Slider, migrationInterval2Slider, migrationInterval3Slider, migrationInterval4Slider;

    //checkboxs
    @FXML
    private CheckBox elitism1CheckBox, elitism2CheckBox, elitism3CheckBox, elitism4CheckBox;

    //progress bar
    @FXML
    private ProgressBar oneProgressBar;

    //buttons
    @FXML
    private Button startButton, cancelButton;

    @FXML
    private void handleStartButtonAction() {
        // Button was clicked, start action ...

    }

    @FXML
    private void handleCancelButtonAction() {
        // Button was clicked, stop action ...

    }

    public ConfigurationMenuController() {

    }

    @FXML
    public void initialize() {

        //handle simple buttons
        startButton.setOnAction((event) -> {
            // Button was clicked, do something...
            outputTextArea.appendText("Button Start\n");
        });
        cancelButton.setOnAction((event) -> {
            // Button was clicked, do something...
            outputTextArea.appendText("Button Canceled\n");
        });

        //hadle checkbuttons
        unidirectionalCircleRadioButton.setOnAction((event) -> {
            boolean selected = unidirectionalCircleRadioButton.isSelected();
            System.out.println("unidirectionalCircleRadioButton (selected: " + selected + ")");
        });
        eachOfEachRadioButton.setOnAction((event) -> {
            boolean selected = eachOfEachRadioButton.isSelected();
            System.out.println("eachOfEachRadioButton (selected: " + selected + ")");
        });
        ladderRadioButton.setOnAction((event) -> {
            boolean selected = ladderRadioButton.isSelected();
            System.out.println("ladderRadioButton (selected: " + selected + ")");
        });
        bidirectionalCircleRadioButton.setOnAction((event) -> {
            boolean selected = bidirectionalCircleRadioButton.isSelected();
            System.out.println("bidirectionalCircleRadioButton (selected: " + selected + ")");
        });


        // Handle CheckBox event
        elitism1CheckBox.setOnAction((event) -> {
            boolean selected = elitism1CheckBox.isSelected();
            System.out.println("CheckBox Action (selected: " + selected + ")");
        });

        elitism2CheckBox.setOnAction((event) -> {
            boolean selected = elitism2CheckBox.isSelected();
            System.out.println("elitism2CheckBox Action (selected: " + selected + ")");
        });

        elitism3CheckBox.setOnAction((event) -> {
            boolean selected = elitism3CheckBox.isSelected();
            System.out.println("elitism3CheckBoxAction (selected: " + selected + ")");
        });

        elitism4CheckBox.setOnAction((event) -> {
            boolean selected = elitism4CheckBox.isSelected();
            System.out.println("elitism4CheckBox (selected: " + selected + ")");
        });

        //handle image views
        eachOfEachImageView.setImage(new Image ("Images/EachOfEach.jpg"));
        bidirectionalCircleImageView.setImage(new Image ("Images/BidirectionalCircle.jpg"));
        ladderImageView.setImage(new Image ("Images/Ladder.jpg"));
        unidirectionalCircleImageView.setImage(new Image ("Images/UnidirectionalCircle.jpg"));
        IntegerProperty count = new SimpleIntegerProperty();
        //handle split menu button
        functionsSplitMenuButton.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            if (isNowShowing) {
                int c = count.get() + 1;
                count.set(c);
                functionsSplitMenuButton.getItems().clear();
                for (int choice = 1; choice <= 3; choice++) {
                    MenuItem mi = new MenuItem("Choice "+choice+" (" + c + ")");
                    functionsSplitMenuButton.getItems().add(mi);
                }
            }
        });

        // Handle Slider value change events.
        mutation1Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Mutation slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        mutation2Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Mutation slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        mutation3Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Mutation slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        mutation4Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Mutation slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        tournament1SelectionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Tournament slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        tournament2SelectionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Tournament slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        tournament3SelectionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Tournament slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        tournament4SelectionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Tournament slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        migrationInterval1Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Migration interval slider Value Changed (newValue: " + newValue.intValue() + ")");
        });
        migrationInterval2Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Migration interval slider  Value Changed (newValue: " + newValue.intValue() + ")");
        });

        migrationInterval3Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Migration interval slider Value Changed (newValue: " + newValue.intValue() + ")");
        });

        migrationInterval4Slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Migration interval slider  Value Changed (newValue: " + newValue.intValue() + ")");
        });


        //handle text field and areas
        printOutput();

        //handle progress bar
        oneProgressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        //tab and labels need to be implemented


    }

    //
    //setting text into right areas
    @FXML
    public void printOutput() {
        minimumTextArea.setText(minimumTextField.getText());
        maximumTextArea.setText(maximumTextField.getText());
        startingPopulation1TextArea.setText(startingPopulation1TextField.getText());
        startingPopulation2TextArea.setText(startingPopulation2TextField.getText());
        startingPopulation3TextArea.setText(startingPopulation3TextField.getText());
        startingPopulation4TextArea.setText(startingPopulation4TextField.getText());
        generations1TextArea.setText(generations1TextField.getText());
        generations2TextArea.setText(generations2TextField.getText());
        generations3TextArea.setText(generations3TextField.getText());
        generations4TextArea.setText(generations4TextField.getText());
        migrationRate1TextArea.setText(migrationRateText1Field.getText());
        migrationRate2TextArea.setText(migrationRateText2Field.getText());
        migrationRate3TextArea.setText(migrationRateText3Field.getText());
        migrationRate4TextArea.setText(migrationRateText4Field.getText());

    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
