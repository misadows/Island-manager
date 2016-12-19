package Island;


public class FitnessCalculator {
    private byte[] solution;

    public void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];


        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    int getFitness(Creature creature) {
        int fitness = 0;

        for (int i = 0; i < creature.genotypeSize() && i < solution.length; i++) {
            if (creature.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    int getTargetSolution() {
        return solution.length;
    }
}
