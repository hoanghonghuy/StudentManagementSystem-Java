package main.java.studentmanagement;

import main.java.studentmanagement.model.Student;
import main.java.studentmanagement.service.StudentService;
import main.java.studentmanagement.utils.Constants;
import main.java.studentmanagement.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
//    public static Student[] studentList =  new Student[Constants.MAX_STUDENTS];
//    public static int studentCount = 0;
    public static List<Student> studentList = FileUtils.loadDataFromFile();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService(scanner);
        while (true) {
            System.out.println("\n--- MENU QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo Mã SV"); // studentId
            System.out.println("4. Cập nhật thông tin sinh viên"); // id
            System.out.println("5. Xóa sinh viên"); // id
            System.out.println("6. Thống kê học lực");
            System.out.println("7. Thống kê điểm trung bình");
            System.out.println("8. Lọc sinh viên theo học lực");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                continue;
            }

            switch (choice) {
                case 1:
                    studentService.createStudent();
                    break;
                case 2:
                    studentService.displayAllStudents();
                    break;
                case 3:
                    studentService.findStudentByStudentId();
                    break;
                case 4:
                    studentService.updateStudent();
                    break;
                case 5:
                    studentService.deleteStudent();
                    break;
                case 6:
                    studentService.displayAcademicRankPercentage();
                    break;
                case 7:
                    studentService.displayGpaPercentage();
                    break;
                case 8:
                    studentService.displayStudentByRank();
                    break;
                case 0:
                    FileUtils.saveDataToFile(studentList);
                    System.out.println("Thoát chương trình!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }
}
