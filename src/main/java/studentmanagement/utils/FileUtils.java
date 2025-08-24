package main.java.studentmanagement.utils;

import main.java.studentmanagement.model.Person;
import main.java.studentmanagement.model.Student;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String FILE_PATH = "students.txt";

    public static void saveDataToFile(List<Student> studentList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : studentList) {
                writer.write(String.valueOf(student.getId()));
                writer. newLine();
                writer.write(student.getName());
                writer.newLine();
                writer.write(student.getDateOfBirth().format(Constants.DATE_FORMATTER));
                writer.newLine();
                writer.write(student.getAddress());
                writer.newLine();
                writer.write(String.valueOf(student.getHeight()));
                writer.newLine();
                writer.write(String.valueOf(student.getWeight()));
                writer.newLine();
                writer.write(student.getStudentId());
                writer.newLine();
                writer.write(student.getUniversityName());
                writer.newLine();
                writer.write(String.valueOf(student.getStartYear()));
                writer.newLine();
                writer.write(String.valueOf(student.getGpa()));
                writer.newLine();
                writer.write("---");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }

    public static List<Student> loadDataFromFile() {
        List<Student> studentList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File dữ liệu không tồn tại. Bắt đầu với danh sách rỗng.");
            return studentList;
        }
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH)) ){
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int id = Integer.parseInt(line);

                    String name = reader.readLine();
                    LocalDate dateOfBirth = LocalDate.parse(reader.readLine(), Constants.DATE_FORMATTER);
                    String address = reader.readLine();
                    double height = Double.parseDouble(reader.readLine());
                    double weight = Double.parseDouble(reader.readLine());
                    String studentId = reader.readLine();
                    String university = reader.readLine();
                    int startYear = Integer.parseInt(reader.readLine());
                    double gpa = Double.parseDouble(reader.readLine());
                    reader.readLine(); // đọc và bỏ qua dòng ngăn cách

                    Student student = new Student(name, dateOfBirth, address, height, weight, studentId, university, startYear, gpa);
                    student.setId(id);
                    studentList.add(student);
                    // cập nhật id lớn nhất tìm được
                    if (id > maxId) {
                        maxId = id;
                    }

                }catch (Exception e){
                    System.out.println(e.getMessage());

                    while ((line = reader.readLine()) != null && !line.equals("---")) {
                        // bỏ qua các dòng lỗi
                    }
                }
            }
            System.out.println("Dữ liệu được tải thành công từ file '" + FILE_PATH + "'.");
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
        }
        // đồng bộ biến đếm id
        Person.updateNextId(maxId + 1);

        return studentList;
    }
}
