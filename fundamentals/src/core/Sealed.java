package core;

import java.util.Collection;
import java.util.List;

public class Sealed {
    sealed interface Shape {}
    record Circle(double radius) implements Shape {}
    record Rectangle(int length, int width) implements Shape {}
    record Square(int length) implements Shape {}
    non-sealed class FilledRectangle implements Shape {}

    public static void main(String[] args) {
        Collection<Shape> myShapes = List.of(
                new Rectangle(10, 20),
                new Circle(10));
        myShapes.stream().map(Sealed::getArea).forEach(System.out::println);
    }

    private static double getArea(Shape myShape) {
        return switch (myShape) {
            case Rectangle(int l, int w) -> l * w;
            case Circle(double r) -> 2 * Math.PI * r;
            case Square s -> s.length * s.length;
            case FilledRectangle f -> 0.0;
            case null -> 0;
        };
    }
}
