package Island;

public interface IslandInterface {
    public void setParameters(IslandParams params);
    public void sendCreature(CreatureParams params);
    public CreatureParams getCreature();
}
