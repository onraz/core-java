package subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitTest {

//    static byte[] transactions = {
//            0b101,
//            0b110,
//            0b111
//    };

    static String[][] transactions = {
            {"apple", "banana", "lemon"},
            {"banana", "berry", "lemon", "orange"},
            {"banana", "berry", "lemon"}
    };

    // P(a) = a
    // P(ab) = a + b
    static Map<String, Integer> lookup = new HashMap<>();
    //static Map<String, Integer> lookupa = Map.of("a", 0b100, "b", 0b010, "c", 0b001);

    public static void main(String[] args) {

        Map<String[], String> map;
        String[] fruits = {"apple", "banana", "berry", "lemon", "orange"};

        for (int fruit = 0; fruit < fruits.length ; fruit++) {
            // 2 ^ N - 1 or 1 bit shifted
            System.out.println(fruits[fruit] + " == " + Integer.toBinaryString((1 << (fruits.length - fruit - 1) )) );
            lookup.put(fruits[fruit], (int) Math.pow(2, fruits.length - fruit - 1));
        }

        int total = (int) Math.pow(2, fruits.length);
        Map<Integer, Integer> freq = new HashMap<>();

        for (String[] transaction : transactions) {
            int binTransaction = toBinary(transaction);
            for (int comb = 1; comb < total; comb++) {
                if ((comb & (comb - 1)) != 0) { // single fruits
                    if ((binTransaction & comb) == comb) {
                        int curr = freq.getOrDefault(comb, 0);
                        freq.put(comb, curr + 1);
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> fruitFreq : freq.entrySet()) {
            System.out.println(toFruits(fruits, fruitFreq.getKey()) + "[" + Integer.toBinaryString(fruitFreq.getKey()) + "] === " + fruitFreq.getValue());
        }
        //System.out.println(total);
        //generateCombinations(List.of("a", "b", "c", "d"));
    }



    static int toBinary(String[] transaction) {
        int bin = 0;
        for (int i = 0; i < transaction.length; i++) {
            int fruit = lookup.get(transaction[i]);
            bin = bin | fruit;
        }
        System.out.println("Processing Transaction " + Arrays.toString(transaction) + " == " + Integer.toBinaryString(bin));
        return bin;
    }

                static List<String> toFruits(String[] fruits, int binary) {
                    List<String> result = new ArrayList<>();
                    for (int i = 0; i < fruits.length; i++) {
                        int fruit = lookup.get(fruits[i]);
                        if ((binary & fruit) != 0) {
                result.add(fruits[i]);
            }
        }
        return result;
    }

    static void generateCombinations(List<String> fruits) {
        int total = (int) (Math.pow(2, fruits.size()));
        System.out.println("Total number of permutations " + total);
        for (int comb = 1; comb < total; comb++) {
            System.out.print(Integer.toBinaryString(comb));
            System.out.print(" == ");
            for (int fruit = 0; fruit < fruits.size(); fruit++) {
                int bitmask = 1 << fruit;
                if ((comb & bitmask) != 0) {
                    System.out.print(fruits.get(fruit) + " ");
                }
            }
            System.out.println();
        }
    }
}
