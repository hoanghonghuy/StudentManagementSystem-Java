package main.java.studentmanagement.model;

import java.time.LocalDate;

public class Student extends Person {
    private String studentId;
    private String universityName;
    private int startYear;
    private double gpa;

    public Student(String name, LocalDate dateOfBirth, String address, double weight, double height,
                   String studentId, String universityName, int startYear, double gpa) {
        super(name, dateOfBirth, address, weight, height);

        this.studentId = studentId;
        this.universityName = universityName;
        this.startYear = startYear;
        this.gpa = gpa;
    }


    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getUniversityName() {
        return universityName;
    }
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    public int getStartYear() {
        return startYear;
    }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student [" + super.toString() +
                ", Mã Sv: '" + studentId + "'| " +
                ", Trường: '" + universityName + "'| " +
                ", Năm nhập học '" + startYear + "'| " +
                ", ĐTB: '" + gpa + "' ]";
    }

}
