package model.streams;

import model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class pipelineTest {
    public static void main(String[] args) {
        String[] names = {"Abi","Adi","Ben","Celine"};
        //before java 8
        List<Student> students = new ArrayList<>();
        for(String name : names){
            students.add(new Student(name));
        }
        System.out.println(students);

        students = Arrays.stream(names)
                .map(Student::new)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(students);

        students = Arrays.stream(names)
                .map(Student::new)
                .collect(LinkedList::new,
                        (studentList,student)->studentList.add(student),
                        (finalStudentList,studentList)->finalStudentList.addAll(studentList));

        students = Arrays.stream(names)
                .map(Student::new)
                .collect(LinkedList::new,
                        List::add,
                        List::addAll);

        Map<String,Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getName,student->student));

        System.out.println( studentMap);
    }
}
