package subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Powerset {

//    public Set<Set<String>> findCombination(Set<String> set){
//
//    }
//
    public static <E> Set<Set<E>> findPowerSet(Set<E> set){
        Set<Set<E>> ret = new HashSet<Set<E>>();
        if(set.isEmpty()){
            return ret;
        }
        ret.add(set); // ab
        Iterator<E> it = set.iterator();
        while(it.hasNext()){ // P(a) = a +P(0) = a; P(ab) = ab + P(_b) + P(a_)  = ab + a + b // P(abc) = abc + P(_bc) + P(a_c) + P (ab_)

            Set<E> tmp = new HashSet<E>(set);   //create a copy of current set
            tmp.remove(it.next());              //remove current element from copy set
            ret.addAll(findPowerSet(tmp));      //recursively find subsets of copy set
        }
        return ret;
    }
    public static HashSet<List<String>> genRecursive(List<String> trans) {
        HashSet<List<String>> toReturn = new HashSet<>();
        if(trans.isEmpty()){
            return toReturn;
        }

        toReturn.add(trans);

        for(int i = 0; i < trans.size(); i++)
        {
            List<String> temp = new ArrayList<>(trans);
            temp.remove(i);
            toReturn.addAll(genRecursive(temp));
        }

        return toReturn;
    }

    public static void main(String[] args) {
//        System.out.println(genRecursive(Arrays.asList("apple", "banana", "lemon"))
//                .stream().filter(s -> s.size() > 1).collect(toList()));
        System.out.println(findPowerSet(Set.of("apple", "banana", "lemon"))
                .stream().filter(s -> s.size() > 1).collect(toList()));
    }

    public static void maissn2(String[] args) {
        Set<Character> set = new HashSet<Character>();
        set.add('a');set.add('b');set.add('c');//set.add('d');
        System.out.println("Input set");        printSet(set);
        System.out.println("\nsub sets");
        findPowerSet(set).stream().forEach(Powerset::printSet);

        System.out.println("\nIter sub sets");
        powerSet(set).stream().forEach(Powerset::printSet);

//        set.
    }

    /**
     * {}:  {}
     *  a:  {} , {a}
     *  b:  {} , {a}, {b}, {b, a}
     *  c:  {} , {a}, {b}, {b, a}, {c}, {c, a}, {c, b}, {c, b, a}
     */
    public static <E> Set<Set<E>> powerSet(Set<E> inputList){

        Set<Set<E>> holder = new HashSet<>();
        holder.add(new HashSet<>());

        for (E item : inputList){

            Set<Set<E>> tempHolder = new HashSet<>();

            for(Set<E> subItem : holder){
                Set<E> comb = new HashSet<>(subItem);comb.add(item);
                tempHolder.add(comb);
            }
            holder.addAll(tempHolder);
        }
        return holder;
    }

    /**
     * {}:  {}
     *  a:  {} , {a}
     *  b:  {} , {a}, {b}, {b, a}
     *  c:  {} , {a}, {b}, {b, a}, {c}, {c, a}, {c, b}, {c, b, a}
     */
    public static List<List<String>> powerSetw(List<String> inputList){

        List<List<String>> allCombs = new ArrayList<>();
        allCombs.add(new ArrayList<>());

        for (String item : inputList){

            List<List<String>> newCombinations = new ArrayList<>();
            for(List<String> subItem : allCombs){
                List<String> comb = new ArrayList<>(subItem);
                comb.add(item);
                newCombinations.add(comb);
            }

            allCombs.addAll(newCombinations);
        }
        return allCombs;
    }

    /**
     *  {1,2,3}
     *
     * Its powerset is the following:
     *
     * {{}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}}
     *
     *  If we take any element of the set (like 1), then the powerset will include an equal number of
     *  sets with these elements ({1}, {1,2}, {1,3}, {1,2,3}), and without these ({}, {2}, {3}, {2,3})
     *
     * powerset({1,2,3}) = powerset({2,3}) + powerset({2,3}).map { it + 1 }
     *
     fun <T> powerset(set: Set<T>): Set<Set<T>> =
         if (set.isEmpty()) {
            setOf(emptySet())
         } else {
            val powersetOfRest = powerset(set.drop(1))
            powersetOfRest + powersetOfRest.map { it + set.first() }
         }
     */

    public static <E> void printSet(Set<E> set){
        StringBuilder sb = new StringBuilder(set.size()==0 ? "{}\n" :"{");
        Iterator<E> it = set.iterator();
        while(it.hasNext()){
            sb.append(it.next().toString())
                    .append(it.hasNext()? ", " : "}\n");
        }
        System.out.print(sb.toString());
    }
}