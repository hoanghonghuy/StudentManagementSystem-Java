package main.java.studentmanagement.repository;

import main.java.studentmanagement.model.Student;
import main.java.studentmanagement.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public class StaticArrayStudentRepository implements IStudentRepository {

    private final Student[] studentList;
    private int studentCount;

    public StaticArrayStudentRepository(List<Student> initialData) {
        this.studentList = new Student[Constants.MAX_STUDENTS];
        this.studentCount = 0;

        // nạp dữ liệu ban đầu vào mảng
        if (initialData != null) {
            for (Student student : initialData) {

                this.add(student);
            }
        }
    }

    @Override
    public void add(Student student) {
        // Chỉ thêm nếu mảng chưa đầy
        if (studentCount < studentList.length) {
            studentList[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cảnh báo: Mảng tĩnh đã đầy, không thể thêm sinh viên mới.");
        }
    }

    @Override
    public void remove(Student studentToRemove) {
        int foundIndex = -1;
        // tìm index của sinh viên cần xóa
        for (int i = 0; i < studentCount; i++) {
            if (studentList[i].getId() == studentToRemove.getId()) {
                foundIndex = i;
                break;
            }
        }

        // nếu tìm thấy, thực hiện dịch chuyển mảng
        if (foundIndex != -1) {
            for (int i = foundIndex; i < studentCount - 1; i++) {
                studentList[i] = studentList[i + 1];
            }
            studentCount--;
            // xoá tham chiếu cuối
            studentList[studentCount] = null;
        }
    }

    @Override
    public Student findById(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (studentList[i].getId() == id) {
                return studentList[i];
            }
        }
        return null;
    }

    @Override
    public Student findByStudentId(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentList[i].getStudentId().equalsIgnoreCase(studentId)) {
                return studentList[i];
            }
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        //chuyển mảng tĩnh thành một ArrayList mới để trả về.
        List<Student> listToReturn = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            listToReturn.add(studentList[i]);
        }
        return listToReturn;
    }

    @Override
    public int size() {
        return this.studentCount;
    }

    @Override
    public boolean isEmpty() {
        return this.studentCount == 0;
    }
}