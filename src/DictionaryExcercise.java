import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionaryExcercise {
    public static void main(String[] args) {
        //find 10 largest words
        try(Stream<String> words=Files.lines(Paths.get("resources/words.txt"))){
            words.sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create a map of count and no of words and return in sorted order of keys
        try(Stream<String> words=Files.lines(Paths.get("resources/words.txt"))){
            words.sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(20)
                    .collect(Collectors.groupingBy(String::length,Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach((es)-> System.out.println("key : "+es.getKey()+" value : "+es.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
