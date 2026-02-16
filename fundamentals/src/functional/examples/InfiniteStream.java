package functional.examples;

import java.util.stream.Stream;

public class InfiniteStream {
    public static void main(String[] args) {
        var naturalNumbers = Stream.iterate(0, i -> i + 1);
        naturalNumbers.limit(10).forEach(System.out::println);
        System.out.println("Squares");
        var squares = Stream.iterate(0, i -> i + 1).mapToInt(i -> i * i);
        squares.limit(10).forEach(System.out::println);
    }
}
