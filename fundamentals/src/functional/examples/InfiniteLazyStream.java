package functional.examples;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteLazyStream {
    public static void main(String[] args) {
        Stream.generate(() -> "Hello")
                .limit(2)
                .forEachOrdered(System.out::println);

        Supplier<UUID> randomUuid = UUID::randomUUID;
        Stream.generate(randomUuid)
                .skip(5)
                .limit(3)
                .forEachOrdered(System.out::println);

        Stream.iterate(10, i -> i * 10)
                .limit(5)
                .forEachOrdered(System.out::println);

        Stream.iterate(1, i -> i <10, i -> i +1)
                .limit(100)
                .forEachOrdered(System.out::println);

        IntStream.range(1, 11)
                .map(i -> i* 10)
                .forEach(System.out::println);

    }
}
