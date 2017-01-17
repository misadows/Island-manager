package Model;

/**
 * Created by Yoshimori on 05/12/2016.
 */
public class Migration {
    private int fromIsland;
    private int toIsland;
    private int epoch;

    public Migration(int fromIsland, int toIsland, int epoch){
        this.fromIsland = fromIsland;
        this.toIsland = toIsland;
        this.epoch = epoch;
    }

    public int getToIsland() {
        return toIsland;
    }

    public void setToIsland(int toIsland) {
        this.toIsland = toIsland;
    }

    public int getFromIsland() {
        return fromIsland;
    }

    public void setFromIsland(int fromIsland) {
        this.fromIsland = fromIsland;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }
}
