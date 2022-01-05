package streams;

import java.util.function.Function;
import java.util.stream.Stream;

public class StreamCreationTest {
    public static void main(String[] args) {
        int s1 = Stream.of(1,2,3,4,5,6,7,8,9,10)
                .mapToInt(i->i.intValue())
                .sum();

        int s2 = Stream.iterate(1,i->i+1)
                .limit(10)
                .reduce(0,(a,b)->a+b);

        Stream.empty().peek(System.out::println);

        Stream.generate(()->2)
                .limit(5)
                .peek(System.out::println)
                .count();
    }
}
