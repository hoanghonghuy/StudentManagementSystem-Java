package main.java.studentmanagement.service;
import main.java.studentmanagement.Main;
import main.java.studentmanagement.model.Student;
import main.java.studentmanagement.utils.Constants;

import java.time.LocalDate;
import java.util.Scanner;

public class StudentService {
    private Scanner scanner;

    public StudentService(Scanner scanner) {
        this.scanner = scanner;
    }

    // Kiểm tra mã sinh viên đã tồn tại trong danh sách chưa
    private boolean isStudentIdExists(String studentId) {
        for (int i = 0; i < Main.studentCount; i++) {
            if (Main.studentList[i].getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }


    public void createStudent() {
        if (Main.studentCount >= Constants.MAX_STUDENTS) {
            System.out.println("Lỗi: Danh sách sinh viên đã đầy, không thể thêm mới.");
            return;
        }

        System.out.println("\n--- Thêm mới sinh viên ---");
        System.out.println("Nhập thông tin chi tiết: ");

        String name;
        LocalDate dateOfBirth;
        String address;
        Double height;
        Double weight;
        String studentId;
        String universityName;
        Integer startYear;
        Double gpa;

        do {
            System.out.println("Nhập tên: ");
            name = Validation.validateName(scanner.nextLine());
        }while (name == null);

        do {
            System.out.println("Nhập ngày sinh (dd-MM-yyyy): ");
            dateOfBirth = Validation.parseDate(scanner.nextLine());
        } while (dateOfBirth == null);

        do {
            System.out.println("Nhập địa chỉ: ");
            address = Validation.validateAddress(scanner.nextLine());
        } while (address == null);

        do {
            System.out.println("Nhập chiều cao: ");
            height = Validation.parseDouble(scanner.nextLine(), Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
        } while (height == null);

        do {
            System.out.println("Nhập cân nặng: ");
            weight =  Validation.parseDouble(scanner.nextLine(), Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
        } while (weight == null);

        do {
            System.out.println("Nhập mã sinh viên: ");
            String input = scanner.nextLine();
            studentId = Validation.validateStudentId(input);
            // Kiểm tra trùng id
            if (studentId != null && isStudentIdExists(studentId)) {
                System.out.println("Lỗi: Mã sinh viên đã tồn tại. Nhập mã khác.");
                studentId = null;
            }

        } while (studentId == null);

        do {
            System.out.println("Nhập tên trường: ");
            universityName = Validation.validateUniversityName(scanner.nextLine());
        } while (universityName == null);

        do {
            System.out.println("Nhập năm nhập học");
            startYear = Validation.parseStartYear(scanner.nextLine());
        } while (startYear == null);

        do {
            System.out.println("Nhập điểm trung bình tích luỹ: ");
            gpa = Validation.parseDouble(scanner.nextLine(), Constants.MIN_GPA, Constants.MAX_GPA);
        } while (gpa == null);


        Student newStudent = new Student(name, dateOfBirth, address, weight, height, studentId, universityName, startYear, gpa);

        Main.studentList[Main.studentCount] = newStudent;
        Main.studentCount++;

        System.out.println("\n THêm sinh viên mới thành công!");
        System.out.println(newStudent.toString());
    }

    public void displayAllStudents() {
        System.out.println("\n--- Danh sách toàn bộ sinh viên ---");

        if (Main.studentCount == 0) {
            System.out.println("Danh sách sinh viên rỗng");
            return;
        }

        for (int i = 0; i < Main.studentCount; i++) {
            System.out.println("STT: " + (i + 1) + ".");
            System.out.println(Main.studentList[i].toString());
            System.out.println("---------------------------------");
        }
    }

    public void findStudentByStudentId() {
        System.out.println("\n--- Tìm kiếm sinh viên theo Mã SV ---");
        if (Main.studentCount == 0) {
            System.out.println("Danh sách sinh viên rỗng");
            return;
        }
        System.out.println("Nhập Mã SV của sinh viên cần tìm: ");
        String searchStudentId = scanner.nextLine().trim();

        if (searchStudentId.isEmpty()) {
            System.out.println("Lỗi: Mã SV không được để trống.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < Main.studentCount; i++) {
            if (Main.studentList[i].getStudentId().equalsIgnoreCase(searchStudentId)) {
                System.out.println("Tìm thấy sinh viên: ");
                System.out.println(Main.studentList[i].toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên nào có ID là '" + searchStudentId + "'.");
        }
    }

    public void updateStudent() {

    }

    public void deleteStudent() {

    }
}
