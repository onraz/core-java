package subset;

import java.util.*;

public class PermutationBestSolution {

    // P(abc) = a + P(bc),  b + P(ac), c + P(ab)

    public static Set<List<String>> findPermutations(List<String> input){
        if (input.isEmpty()){
            return Collections.emptySet();
        } else if (input.size() == 1) {
            return Set.of(input);
        }

        Set<List<String>> ret = new HashSet<>();
        //ret.add(List.copyOf(input)); // or if (input.length() == 1 ) return Set.of(input);

        for (String item : input) {
            final List<String> rest = new ArrayList<>(input);
            rest.remove(item);
            Set<List<String>> permsWithoutItems = findPermutations(rest);

            // ret.addAll(permsWithoutItems); for all size permutations

            for (List<String> permsWithoutItem : permsWithoutItems) {
                List<String> list = new ArrayList<>();
                list.add(item);
                list.addAll(permsWithoutItem);
                ret.add(list);
            }
        }

        return ret;
    }


    public static void main(String[] args) {
        Set<List<String>> permutations = findPermutations(Arrays.asList("a", "b"));
        System.out.println(permutations);
        System.out.println("Total permutations: " + permutations.size());
    }
}
