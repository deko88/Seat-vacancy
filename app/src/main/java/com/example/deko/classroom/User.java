package com.example.deko.classroom;

public class User {

    private String studentName, studentEmail, studentYear, studentSpecialization;


    public User(String studentName, String studentEmail, String studentYear, String studentSpecialization) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentYear = studentYear;
        this.studentSpecialization = studentSpecialization;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentSpecialization() {
        return studentSpecialization;
    }

    public void setStudentSpecialization(String studentSpecialization) {
        this.studentSpecialization = studentSpecialization;
    }
}