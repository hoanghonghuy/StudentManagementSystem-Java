package main.java.studentmanagement.repository;

import main.java.studentmanagement.model.Student;
import java.util.List;


 // định nghĩa các thao tác mà một kho lưu trữ sinh viên phải có
public interface IStudentRepository {
    void add(Student student);
    void remove(Student student);
    Student findById(int id);
    Student findByStudentId(String studentId);
    //luôn trả về List để Service xử lý đồng nhất
    List<Student> getAll();
    int size();
    boolean isEmpty();
}