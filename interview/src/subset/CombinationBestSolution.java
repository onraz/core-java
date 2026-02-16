package subset;

import java.util.*;

public class CombinationBestSolution {

    // C(abc) = abc + C(_bc) + C(a_c) + C(ab_)
    public static Set<Set<String>> findPowerSet(Set<String> set){
        if(set.isEmpty()){
            return Collections.emptySet(); // Set.of(Set.of()) to include empty set
        }

        Set<Set<String>> ret = new HashSet<>();
        ret.add(set); // abc

        for (String item : set) {
            final HashSet<String> rest = new HashSet<>(set);
            rest.remove(item);
            ret.addAll(findPowerSet(rest));
        }

        return ret;
    }


    public static void main(String[] args) {
        Set<Set<String>> powerSet = findPowerSet(new HashSet<>(Arrays.asList("a", "b", "c")));
        System.out.println(powerSet);
        System.out.println("Total combinations: " + powerSet.size()); // empty set excluded
    }
}
