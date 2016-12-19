import Island.Island;
import Island.IslandParams;


public class Main {

    public static void main(String [ ] args){
        Island island = new Island();
        IslandParams islandParams= new IslandParams(100, 0.5, 0.5, 0.5, 4, true);
        island.setParameters(islandParams);

        for(int i=0; i<10; i++){
            island.nextEpoch();
        }
    }
}
