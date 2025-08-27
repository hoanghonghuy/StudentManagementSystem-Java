package main.java.studentmanagement.model;

import main.java.studentmanagement.utils.Constants;

import java.time.LocalDate;

public class Person {
    private static int nextId = 1;
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private double weight;
    private double height;

    public Person(String name, LocalDate dateOfBirth, String address, double weight, double height) {
        this.id = nextId++;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.weight = weight;
        this.height = height;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static void updateNextId(int newNextId) {
        nextId = newNextId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Id: '" + id + "' |" +
                " Tên: '" + name + "'| " +
                " Ngày sinh: " + dateOfBirth.format(Constants.DATE_FORMATTER) + "'| " +
                " Địa chỉ: '" + address + "'| " +
                " Chiều cao: '" + height + "'| " +
                " Cân nặng: '" + weight + "'| \n";
    }
}
