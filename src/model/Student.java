package model;

import java.util.List;

public class Student {
    String name;
    Double marks;
    List<Subject> subjects;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name,Double marks) {
        this.name = name;
        this.marks = marks;
    }

    public Student(String name, Double marks, List<Subject> subjects) {
        this.name = name;
        this.marks = marks;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
