package streams;

import model.Student;
import model.Subject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTransformation {
    public static void main(String[] args) {
        // sort students increasing order of names
        Subject economic = new Subject("Economics");
        Subject maths = new Subject("Maths");
        Subject english = new Subject("English");
        Subject physics = new Subject("Physics");
        List<Student> students = List.of(
                new Student("Abi",90.5 , List.of(economic,physics)),
                new Student("Adil",92.5),
                new Student("Basil",65.3,List.of(maths)),
                new Student("Bismi",86.5,List.of(physics,economic,maths)),
                new Student("Dimal",90.5,List.of(english,maths,economic))
        );

        List<Student> studentSorted =  students.stream()
                .sorted(Comparator.comparing(
                        Student::getName,
                        String::compareTo
                ))
                .distinct()
                .collect(Collectors.toList());

        List<Student> studentsWithDistinction = students.stream()
                .filter(s->s.getMarks()>80)
                .sorted(Comparator.comparingDouble(Student::getMarks)
                        .reversed()
                        .thenComparing(Comparator.comparing(Student::getName).reversed()))
                .collect(Collectors.toList());

        //get list of all subjects

        List<Subject> subjects = students.stream()
                .flatMap(s->{
                    if(null==s.getSubjects())
                        return Stream.of();
                    return s.getSubjects().stream();
                })
                .distinct()
                .collect(Collectors.toList());

        System.out.println(subjects);
    }
}
