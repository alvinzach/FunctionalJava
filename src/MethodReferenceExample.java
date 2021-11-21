import model.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class MethodReferenceExample {

    public static void main(String[] args) {
        List<String> studentNames = List.of("ken","thompson","ben","evans");
        studentNames
                .stream()
                .map(student -> student.toUpperCase())
                .map((studentName)->new Student(studentName))
                .forEach(student -> System.out.println(student));

        studentNames
                .stream()
                .map(String::toUpperCase)
                .map(Student::new)
                .forEach(System.out::println);

    }
}
