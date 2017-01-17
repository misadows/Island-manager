package Visualisation;

import Model.Migration;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Yoshimori on 07/01/2017.
 */
public class AnimationController {
    private MainApp mainApp;
    private Queue<TranslateTransition> transitions;
    public static final int TIME = 10000;

    @FXML
    private ImageView creature;

    public void startAnimation(ArrayList<Migration> migrations, int generations){
        Migration migration;
        int time = TIME/generations;
        for(int i=0; i<generations; i++){
            migration = migrations.get(i);
            transitions.add(createMigrationAnimation(migration, time));
            transitions.poll().play();
        }
    }

    @FXML
    public void handleIslandClicked(ActionEvent event)
    {
        String island = event.getSource().toString();
        this.mainApp.showCharts((island.charAt(10)-'0'));
    }

    private TranslateTransition createMigrationAnimation(Migration migration, int time) {
        int from = migration.getFromIsland();
        int to = migration.getToIsland();

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(time));

        creature.setOpacity(1);
        transition.setNode(creature);
        // If island is 0 or 2 then we setFromX = 20 otherwise 444 px
        // If island is 0 or 2 and island where is going is 1 or 3 we setToX = 444 otherwise 20
        //Set x transition
        transition.setFromX(from%2 == 0 ? 20 : 444);
        transition.setToX((from%2 == 0 && to%2 != 0) ? 444 : 20);

        // If island is 0 or 1 then we setFromY = 20 otherwise 444 px
        // If island is 0 or 1 and island where is going is 2 or 3 we setToX = 444 otherwise 20
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