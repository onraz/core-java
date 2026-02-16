package sliding_window;

public class FrogHorseSimulation {

    private static final String SOUND = "neigh";
    public static void main(String[] args) {
        String input = "nnneeeiiiggghhhnneeiigghhneigh";
        System.out.println(minAnimals(input));
    }

    public static int minAnimals(String input) {
        int[] freq = new int[SOUND.length()];
        int activeAnimal = 0;
        int maxActiveAnimal = 0;

        for (char ch : input.toCharArray()) {
            int index = SOUND.indexOf(ch);
            if (index == -1) {
                return -1;
            }

            freq[index]++;
            if (index == 0) {
                activeAnimal++;
                maxActiveAnimal = Math.max(maxActiveAnimal, activeAnimal);
            } else {
                if (freq[index - 1] < freq[index]) {
                    return -1;
                }

                if (index == (SOUND.length() - 1)) {
                    activeAnimal--;
                }
            }

        }
        if (activeAnimal != 0) {
            return -1;
        }
        return maxActiveAnimal;
    }
}
