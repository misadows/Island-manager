import Island.Island;
import Island.IslandParams;


public class Main {

    public static void main(String [ ] args){
        Island island = new Island(new IslandParams(100, 0.1, 0.5, 0.1, 40, true, "1110000110101010100001110100110101111000101110101101010110111101"));

        for(int i=0; i<1000; i++){
            island.nextEpoch();
        }
    }
}
