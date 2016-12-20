package Island;


public class Creature {
    private byte[] genes;

    public Creature(int genotypeSize){
        generateRandomGenotype(genotypeSize);
    }
    public Creature(Creature c){
        genes = new byte[c.genotypeSize()];
        for(int i=0; i<c.genotypeSize(); i++){
            genes[i] = c.getGene(i);
        }
    }

    private void generateRandomGenotype(int genotypeLength){
        genes = new byte[genotypeLength];
        for(int i=0; i<genotypeLength; i++){
            genes[i] = (byte) Math.round(Math.random());
        }
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
    }

    public int genotypeSize() {
        return genes.length;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genotypeSize(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
