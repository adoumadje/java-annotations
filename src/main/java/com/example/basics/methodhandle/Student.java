package com.example.basics.methodhandle;

public class Student {
    public static int numOfStudent;
    private String name;
    private String course;

    public Student() {
        numOfStudent++;
    }

    public Student(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public static void setNumOfStudent(int numOfStudent) {
        Student.numOfStudent = numOfStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
