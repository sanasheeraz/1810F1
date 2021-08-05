package com.example.databasedemoapp;

public class Student {
    public int Id;
    public String Name;
    public String Course;
    public int Fees;

    public Student(int id, String name, String course, int fees) {
        Id = id;
        Name = name;
        Course = course;
        Fees = fees;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int getFees() {
        return Fees;
    }

    public void setFees(int fees) {
        Fees = fees;
    }
}
