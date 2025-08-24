package main.java.studentmanagement.service;
import main.java.studentmanagement.Main;
import main.java.studentmanagement.enums.AcademicRank;
import main.java.studentmanagement.model.Student;
import main.java.studentmanagement.utils.Constants;

import java.time.LocalDate;
import java.util.*;

public class StudentService {
    private Scanner scanner;

    public StudentService(Scanner scanner) {
        this.scanner = scanner;
    }

    // Kiểm tra mã sinh viên đã tồn tại trong danh sách chưa
    private boolean isStudentIdExists(String studentId) {
        for (Student student : Main.studentList) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }


    public void createStudent() {
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
            System.out.print("Nhập tên: ");
            name = Validation.validateName(scanner.nextLine());
        }while (name == null);

        do {
            System.out.print("Nhập ngày sinh (dd-MM-yyyy): ");
            dateOfBirth = Validation.parseDate(scanner.nextLine());
        } while (dateOfBirth == null);

        do {
            System.out.print("Nhập địa chỉ: ");
            address = Validation.validateAddress(scanner.nextLine());
        } while (address == null);

        do {
            System.out.print("Nhập chiều cao (cm): ");
            height = Validation.parseDouble(scanner.nextLine(), Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
        } while (height == null);

        do {
            System.out.print("Nhập cân nặng (kg): ");
            weight =  Validation.parseDouble(scanner.nextLine(), Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
        } while (weight == null);

        do {
            System.out.print("Nhập mã sinh viên: ");
            String input = scanner.nextLine();
            studentId = Validation.validateStudentId(input);
            // Kiểm tra trùng id
            if (studentId != null && isStudentIdExists(studentId)) {
                System.out.println("Lỗi: Mã sinh viên đã tồn tại. Nhập mã khác.");
                studentId = null;
            }

        } while (studentId == null);

        do {
            System.out.print("Nhập tên trường: ");
            universityName = Validation.validateUniversityName(scanner.nextLine());
        } while (universityName == null);

        do {
            System.out.print("Nhập năm nhập học");
            startYear = Validation.parseStartYear(scanner.nextLine());
        } while (startYear == null);

        do {
            System.out.print("Nhập điểm trung bình tích luỹ: ");
            gpa = Validation.parseDouble(scanner.nextLine(), Constants.MIN_GPA, Constants.MAX_GPA);
        } while (gpa == null);


        Student newStudent = new Student(name, dateOfBirth, address, weight, height, studentId, universityName, startYear, gpa);

        Main.studentList.add(newStudent);

        System.out.println("\n THêm sinh viên mới thành công!");
        System.out.println(newStudent.toString());
    }

    public void displayAllStudents() {
        System.out.println("\n--- Danh sách toàn bộ sinh viên ---");

        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng");
            return;
        }

        int stt = 1;
        for (Student student : Main.studentList) {
            System.out.println("STT: " + (stt++) + ".");
            System.out.println(student.toString());
            System.out.println("---------------------------------");
        }
    }


    public void findStudentByStudentId() {
        System.out.println("\n--- Tìm kiếm sinh viên theo Mã SV ---");
        if (Main.studentList.isEmpty()) {
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
        for (Student student : Main.studentList) {
            if (student.getStudentId().equalsIgnoreCase(searchStudentId)) {
                System.out.println("Tìm thấy sinh viên: ");
                System.out.println(student.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên nào có ID là '" + searchStudentId + "'.");
        }
    }

    private Student findStudentObjectById(int id) {
        for (Student student : Main.studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent() {
        System.out.println("\n---Cập nhật thông tin sinh viên ---");
        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng");
            return;
        }
        System.out.println("Nhập Id của sinh viên cần cập nhật: ");
        int idToUpdate;
        try {
            idToUpdate = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Id phải là một con số.");
            return;
        }

        Student studentToUpdate = findStudentObjectById(idToUpdate);
        if (studentToUpdate == null) {
            System.out.println("Không tìm thấy sinh viên với Id: '" + idToUpdate + "'.");
            return;
        }

        System.out.println("Tìm thấy sinh viên. Thông tin hiện tại là:");
        System.out.println(studentToUpdate.toString());

        while (true) {
            System.out.println("\n--- Chọn thông tin cần sửa ---");
            System.out.println("1. Tên");
            System.out.println("2. Ngày sinh");
            System.out.println("3. Địa chỉ");
            System.out.println("4. Chiều cao");
            System.out.println("5. Cân nặng");
            System.out.println("6. Tên trường");
            System.out.println("7. Năm nhập học");
            System.out.println("8. Điểm TB");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Lựa chọn của bạn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ.");
                continue;
            }

            switch (choice) {
                case 1:
                    String newName;
                    do {
                        System.out.println("Nhập tên mới: ");
                        newName = Validation.validateName(scanner.nextLine());
                    } while (newName == null);
                    studentToUpdate.setName(newName);
                    System.out.println("Cập nhật tên thành công.");
                    break;

                case 2:
                    LocalDate newDateOfBirth;
                    do {
                        System.out.print("Nhập ngày sinh mới (dd-MM-yyyy): ");
                        newDateOfBirth = Validation.parseDate(scanner.nextLine());
                    } while (newDateOfBirth == null);
                    studentToUpdate.setDateOfBirth(newDateOfBirth);
                    System.out.println("Cập nhật ngày sinh thành công.");
                    break;

                case 3:
                    String newAddress;
                    do {
                        System.out.print("Nhập địa chỉ mới: ");
                        newAddress = Validation.validateAddress(scanner.nextLine());
                    } while (newAddress == null);
                    studentToUpdate.setAddress(newAddress);
                    System.out.println("Cập nhật địa chỉ thành công.");
                    break;
                case 4:
                    Double newHeight;
                    do {
                        System.out.print("Nhập chiều cao mới (cm): ");
                        newHeight = Validation.parseDouble(scanner.nextLine(), Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
                    } while (newHeight == null);
                    studentToUpdate.setHeight(newHeight);
                    System.out.println("Cập nhật chiều cao thành công.");
                    break;

                case 5:
                    Double newWeight;
                    do {
                        System.out.print("Nhập cân nặng mới (kg): ");
                        newWeight = Validation.parseDouble(scanner.nextLine(), Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
                    } while (newWeight == null);
                    studentToUpdate.setWeight(newWeight);
                    System.out.println("Cập nhật cân nặng thành công.");
                    break;

                case 6:
                    String newUniversityName;
                    do {
                        System.out.println("Nhập tên trường mới: ");
                        newUniversityName = Validation.validateUniversityName(scanner.nextLine());
                    } while (newUniversityName == null);
                    studentToUpdate.setUniversityName(newUniversityName);
                    System.out.println("Cập nhật tên trường thành công.");
                    break;

                case 7:
                    Integer newStartYear;
                    do {
                        System.out.println("Nhập năm nhâp học mới: ");
                        newStartYear = Validation.parseStartYear(scanner.nextLine());
                    } while (newStartYear == null);
                    studentToUpdate.setStartYear(newStartYear);
                    System.out.println("Cập nhật năm nhập học thành công.");
                    break;

                case 8:
                    Double newGpa;
                    do {
                        System.out.println("Nhập điểm trung bình tích luỹ mới: ");
                        newGpa = Validation.parseDouble(scanner.nextLine(), Constants.MIN_GPA, Constants.MAX_GPA);
                    } while (newGpa == null);
                    studentToUpdate.setGpa(newGpa);
                    System.out.println("Cập nhật điểm trung bình tích luỹ thành công.");
                    break;

                case 0:
                    System.out.println("\n--- Thông tin sinh viên sau khi cập nhật ---");
                    System.out.println(studentToUpdate.toString());
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ, hãy chọn từ 0 - 8.");
                    break;
            }
        }
    }

    public void deleteStudent() {
        System.out.println("\n--- Xóa sinh viên ---");

        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }

        System.out.print("Nhập Id của sinh viên cần xóa: ");
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: ID phải là một con số hợp lệ.");
            return;
        }

        Student studentToDelete = findStudentObjectById(idToDelete);
        if (studentToDelete != null) {
            Main.studentList.remove(studentToDelete);
            System.out.println("Xoá thành công sinh viên có Id: '" + idToDelete + "'.");
        } else {
            System.out.println("Không tìm thấy sinh viên có Id: '" + idToDelete + "'.");
        }
    }

    // % học lực của sinh viên trong danh sách
    public void displayAcademicRankPercentage() {
        System.out.println("\n--- Thống kê học lực của sinh viên ---");
        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }

        Map<AcademicRank, Integer> rankCounts = new HashMap<>();

        // duyệt qua danh sách
        for (Student student : Main.studentList) {
            AcademicRank academicRank = student.getAcademicRank();

//            if (rankCounts.containsKey(academicRank)) {
//                int currentCount = rankCounts.get(academicRank);
//                currentCount++;
//                rankCounts.put(academicRank, currentCount);
//            } else {
//                rankCounts.put(academicRank, 1);
//            }
            // Lấy số đếm hiện tại của academicRank, nếu chưa có thì là 0, sau đó cộng thêm 1
            rankCounts.put(academicRank, rankCounts.getOrDefault(academicRank, 0) + 1);
        }

        // hiển thị
        int totalStudents = Main.studentList.size();
        System.out.println("Tổng số sinh viên: '" +  totalStudents + "'.\n");

        // mảng chứa enum theo thứ tự
        AcademicRank[] allAcademicRanks = AcademicRank.values();
        // duyệt ngược mảng để lấy thứ tự cao - thấp
        for (int i = allAcademicRanks.length - 1; i >= 0; i--) {
            AcademicRank academicRank = allAcademicRanks[i];

            // lấy số lượng của loại học lực hiện tại, mặc định = 0
            int count = rankCounts.getOrDefault(academicRank, 0);

            double percentage = (double) count / (double) totalStudents * 100;

            System.out.printf("- %-15s: %d (%.2f%%)\n", academicRank.getDescription(), count, percentage);
        }

    }

    // % điểm trung bình của sinh viên trong danh sách
    public void displayGpaPercentage(){
        System.out.println("\n--- Thống kê điểm trung bình ---");

        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }

        // lưu trữ kết quả đems
        Map<Double, Integer> gpaCounts = new HashMap<>();

        for (Student student : Main.studentList) {
            Double gpa = student.getGpa();
            //lấy số điểm hiện tại, nếu chưa có trong Map thì mặc định = 0, sau đó tăng lên 1
            gpaCounts.put(gpa, gpaCounts.getOrDefault(gpa, 0) + 1);
        }

        int totalStudents = Main.studentList.size();
        System.out.println("Tổng số sinh viên: '" +  totalStudents + "'.\n");
        System.out.println("Kết quả thống kê tần suất điểm: ");

        // duyệt Map lấy cả key, value
        for (Map.Entry<Double, Integer> entry : gpaCounts.entrySet()) {
            double gpa = entry.getKey();
            int count = entry.getValue();
            double percentage = (double) count / (double) totalStudents * 100;

            System.out.printf("- Điểm %.1f: %d sinh viên (%.2f%%)\n", gpa, count, percentage);
        }
    }

    public void displayStudentByRank(){
        System.out.println("\n--- Hiển thị danh sách sinh viên theo học lực ---");

        if (Main.studentList.isEmpty()) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }

        AcademicRank[] allAcademicRanks = AcademicRank.values();
        System.out.println("Chọn học lực để hiển thị:");

        for (int i = 0; i < allAcademicRanks.length; i++) {
            // tạo thứ tự từ 1, 2, 3, ...
            System.out.printf("%d. %s \n", (i + 1), allAcademicRanks[i].getDescription());
        }
        System.out.print("Lựa chọn của bạn: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > allAcademicRanks.length) {
                System.out.println("Lựa chọn không hợp lệ.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
            return;
        }

        AcademicRank targetAcademicRank = allAcademicRanks[choice - 1];
        List<Student> foundStudents = new ArrayList<>();
        for (Student student : Main.studentList) {
            if (student.getAcademicRank() == targetAcademicRank) {
                foundStudents.add(student);
            }
        }

        if (foundStudents.isEmpty()) {
            System.out.println("\nKhông tìm thấy sinh viên nào có học lực '" + targetAcademicRank.getDescription() + "'");
        } else {
            System.out.println("\n--- Danh sách sinh viên có học lực '" + targetAcademicRank.getDescription() + "' ---");
            int stt = 1;
            for (Student student : foundStudents) {
                System.out.println("STT " + stt++ + ": ");
                System.out.println(student.toString());
                System.out.println("--------------------------");
            }
        }
    }
}
