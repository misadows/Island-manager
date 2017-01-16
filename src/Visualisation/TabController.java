//package Visualisation;
//
//import javafx.fxml.FXML;
//import javafx.scene.Node;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.Slider;
//import javafx.scene.layout.AnchorPane;
//
//import java.awt.*;
//
//public class TabController {
//    private ConfigurationMenuController configurationMenuController;
//
//    public TabController() {}
//
//    @FXML
//    private void initialize() {
//
//    }
//
//    public void setConfigurationMenuController(ConfigurationMenuController configurationMenuController) {
//        this.configurationMenuController = configurationMenuController;
//    }
////    // It need to be refactor
////    private List<IslandParams> islands;
//    public static final String TS =" 1110000110101010100001110100110101111000101110101101010110111101";
//    public static final int TOURNAMENT_MIN_SIZE = 1;
//    public static final int TOURNAMENT_MAX_SIZE = 1000;
//    public static final int MIN_STARTING_POPULATION = 1;
//    public static final int MAX_STARTING_POPULATION = 1000000;
//
//    private int startingPopulation, tournamentSize;
//    private double basicCrossover, basicMutation, singlePointCrossover, singlePointMutation, migrationRate;
//    private boolean elitism;
//    private String targetSolution;
////    ToggleGroup group = new ToggleGroup();
////
////    @FXML
////    private TextField population, tournament_size;
////    @FXML
////    private Slider basic_crossover, basic_mutation, single_point_mutation, single_point_crossover, migration;
////    //@FXML
////    //private CheckBox elitism;
////
////    @FXML
////    private AnchorPane  islandsForm;
////    private int startingPopulation, tournamentSize;
////    private double basicCrossover, basicMutation, singlePointCrossover, singlePointMutation, migrationRate;
////    private boolean elitism;
////    private String targetSolution;
////    private Alert alert = new Alert(Alert.AlertType.ERROR);
////
////
//    public boolean addIslandParams(AnchorPane ap, String tab) {
//        for(Node node : ap.getChildren()) {
//            if(node.getId() == null){
//                continue;
//            }
//            try{
//                switch(node.getId()){
//                    case "population":
//                        startingPopulation = Integer.parseInt(((TextField) node).getText());
//                        if(startingPopulation < MIN_STARTING_POPULATION || startingPopulation > MAX_STARTING_POPULATION) {
//                            throw new FormException("Population must be between"+MIN_STARTING_POPULATION+" and " + MAX_STARTING_POPULATION);
//                        }
//                        break;
//                    case "basic_crossover":
//                        basicCrossover = ((Slider) node).getValue();
//                        break;
//                    case "basic_mutation":
//                        basicMutation = ((Slider) node).getValue();
//                        break;
//                    case "single_point_mutation":
//                        singlePointMutation = ((Slider) node).getValue();
//                        break;
//                    case "single_point_crossover":
//                        singlePointCrossover = ((Slider) node).getValue();
//                        break;
//                    case "migration":
//                        migrationRate = ((Slider) node).getValue();
//                        break;
//                    case "tournament_size":
//                        tournamentSize = Integer.parseInt(((TextField) node).getText());
//                        if(tournamentSize < TOURNAMENT_MIN_SIZE || tournamentSize > TOURNAMENT_MAX_SIZE){
//                            throw new FormException("Tournament selection must be between" + TOURNAMENT_MIN_SIZE + " and " + TOURNAMENT_MAX_SIZE);
//                        }
//                        break;
//                    case "elitism":
//                        elitism = ((CheckBox) node).isSelected();
//                        break;
//
//                    default:
//                        System.out.println("Invalid node id");
//
//                }
//            }
//            catch(Exception e){
//                configurationMenuController.showErrorDialog("While parsing: " + node.getId() + "in tab:"+ tab + "\nError:" + e.getMessage());
//                return false;
//            }
//        }
//        this.islands.add(new IslandParams(startingPopulation, migrationRate, basicCrossover, basicMutation,
//                singlePointCrossover, singlePointMutation, tournamentSize, elitism, targetSolution));
//        return true;
//    }
////
////    private void disableForms(boolean form) {
////        islandsForm.setDisable(form);
////    }
////
////    public void showErrorDialog(String message) {
////        alert.setContentText(message);
////        alert.showAndWait();
////        disableForms(false);
////    }
////
////
////
////
////    @FXML
////    private void initialize(){
////        startingPopulation = 1000;
////        tournamentSize = 10;
////        basicCrossover = 0.1;
////        basicMutation = 0.1;
////        singlePointCrossover = 0.1;
////        singlePointMutation = 0.1;
////        migrationRate = 0.1;
////        elitism = true;
////
////    }
////    public TabController() {}
//}
