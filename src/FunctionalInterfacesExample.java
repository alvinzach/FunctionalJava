import com.sun.jdi.InconsistentDebugInfoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfacesExample {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.startsWith("k");
        Consumer<String> consumer = System.out::println;

        List.of("ken", "karen", "ben")
                .stream().filter(predicate)
                .forEach(consumer);


        BiPredicate<String,String> reduplicatedName = String::equalsIgnoreCase;
        // (a,b) - > a.equalsIgnoreCase(b);
        BinaryOperator<String> concatNames = String::concat;
        //BiFunction<String,String,String> concatNames = (s1,s2)->s1.concat(s2);

        IntConsumer intConsumer = (i) -> System.out.println(Math.sqrt(i));
        intConsumer.accept(10);
    }
}
