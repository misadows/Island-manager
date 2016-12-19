package Island;


public interface IslandInterface {
    public void setParameters(IslandParams params);
    public void sendCreature(Creature creature);
    public Creature getCreature();
    public void nextEpoch();
    public Results getResults();
}
