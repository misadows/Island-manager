package Visualisation;

import Model.Migration;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Yoshimori on 07/01/2017.
 */
public class AnimationController {
    private MainApp mainApp;
    private Queue<TranslateTransition> transitions;

    @FXML
    private ImageView creature;

    //          !!!Attention!!!
    // Migrations animation example
    // Now migrations are bind to IslandClicked
    // But this is ONLY FOR DEMONSTRATION PURPOSES!!!
    // We wait until Topology Module prepare proper code to handle this events
    // To test animation comment: "this.mainApp.showCharts(0);"
    // And uncomment: "transitions.add(createMigrationAnimation(new Migration(0,1)));" && "transitions.poll().play();"

    // TODO
    // To run animations simultaneously in one epoch I will be using e.g.
    // Source: http://www.dummies.com/programming/java/how-to-combine-transitions-in-javafx/
    //    ParallelTransition p = new ParallelTransition()
    //      p.getChildren().add(t1);
    //      p.getChildren().add(t2);
    //      p.getChildren().add(t3);
    //      p.play();
    
    // Time for animation will be constant e.g. 60 sek
    // So duration of one epoch(animations) will be: ( 60000/epochs millis)

    // Nie definiować na sztywno wysp!!!
    @FXML
    public void handleIsland1Clicked(){
        System.out.println("Island 1 was clicked");
//        transitions.add(createMigrationAnimation(new Migration(0,1)));
//        transitions.add(createMigrationAnimation(new Migration(0,2)));
//        transitions.add(createMigrationAnimation(new Migration(0,3)));
//        transitions.poll().play();
        this.mainApp.showCharts(0);
    }
    @FXML
    public void handleIsland2Clicked(){
        System.out.println("Island 2 was clicked");
//        transitions.add(createMigrationAnimation(new Migration(1,0)));
//        transitions.add(createMigrationAnimation(new Migration(1,2)));
//        transitions.add(createMigrationAnimation(new Migration(1,3)));
//        transitions.poll().play();
        this.mainApp.showCharts(1);
    }
    @FXML
    public void handleIsland3Clicked(){
        System.out.println("Island 3 was clicked");
//        transitions.add(createMigrationAnimation(new Migration(2,1)));
//        transitions.add(createMigrationAnimation(new Migration(2,0)));
//        transitions.add(createMigrationAnimation(new Migration(2,3)));
//        transitions.poll().play();
        this.mainApp.showCharts(2);
    }
    @FXML
    public void handleIsland4Clicked(){
        System.out.println("Island 4 was clicked");
//        transitions.add(createMigrationAnimation(new Migration(3,1)));
//        transitions.add(createMigrationAnimation(new Migration(3,2)));
//        transitions.add(createMigrationAnimation(new Migration(3,0)));
//        transitions.poll().play();
        this.mainApp.showCharts(3);
    }

    private TranslateTransition createMigrationAnimation(Migration migration) {
        int from = migration.getFromIsland();
        int to = migration.getToIsland();

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(2000));

        creature.setOpacity(1);
        transition.setNode(creature);
        //soluting equation of Carno's table
        // 11. Opisać co tu się do chuja Pana wyprawia
        //Set x transition
        transition.setFromX(from%2 == 0 ? 20 : 444);
        transition.setToX((from%2 == 0 && to%2 != 0) ? 444 : 20);

        //Set y transition
        transition.setFromY(from <= 1 ?  20 : 444);
        transition.setToY((from > 1 && to <= 1) ? 444 : 20);

        transition.setOnFinished((e)->{
            creature.setOpacity(0);
        });
        return transition;
    }

    public AnimationController() {}

    @FXML
    private void initialize() {
        creature.setOpacity(0);
        transitions = new ArrayDeque<>();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}