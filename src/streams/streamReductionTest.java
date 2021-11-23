package streams;

import model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class streamReductionTest {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Abi",90.5),
                new Student("Adil",93.5),
                new Student("Basil",65.3),
                new Student("Bismi",86.5),
                new Student("Dimal",98.5)
        );
        Student maxMark = students.stream()
                .max(Comparator.comparingDouble(Student::getMarks)).get();

        Optional<Student> minMark = students.stream()
                .min(Comparator.comparingDouble(Student::getMarks));

        long totalStudents = students.stream().count();

        System.out.println("\nmax Mark : " + maxMark + "\nmin Mark : " + minMark + "\ntotal students : " + totalStudents);

        Double sum = students.stream()
                .mapToDouble(Student::getMarks)
                .sum();

        Double avg = students.stream()
                .mapToDouble(Student::getMarks)
                .average().getAsDouble();

        boolean markAbove90 = students.stream().allMatch(student -> student.getMarks()>90);

        boolean anyOneAbove90 = students.stream().anyMatch(student -> student.getMarks()>90);

        Student findOneStudentAbove90 = students.stream().findAny().get();
    }

}
