package main.java.studentmanagement.repository;

import main.java.studentmanagement.model.Student;
import java.util.ArrayList;
import java.util.List;


public class ArrayListStudentRepository implements IStudentRepository {

    private List<Student> studentList;


    public ArrayListStudentRepository(List<Student> initialData) {
        this.studentList = new ArrayList<>(initialData);
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public void remove(Student student) {
        studentList.remove(student);
    }

    @Override
    public Student findById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Student findByStudentId(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        // trả về 1 bản sao của danh sách tránh sửa đổi trực tiếp từ bên ngoài
        return new ArrayList<>(studentList);
    }

    @Override
    public int size() {
        return studentList.size();
    }

    @Override
    public boolean isEmpty() {
        return studentList.isEmpty();
    }
}