package main.java.studentmanagement.service;

import main.java.studentmanagement.utils.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {

    public static String validateName(String input) {
        String name = input.trim();
        if (name.isEmpty() || name.length() >= Constants.MAX_NAME_LENGTH) {
            System.out.println("Lỗi: Tên không được để trống và phải ít hơn '" + Constants.MAX_NAME_LENGTH + "' ký tự!");
            return null;
        }
        return name;
    }

    public static LocalDate parseDate(String input) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate date = LocalDate.parse(input.trim(),  Constants.DATE_FORMATTER);
            if(date.getYear() < Constants.MIN_BIRTH_YEAR || date.isAfter(LocalDate.now())) {
                System.out.println("Lỗi: Năm sinh phải từ '" + Constants.MIN_BIRTH_YEAR + "' và không được vượt quá ngày hôm nay!");
                return null;
            }
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Định dạng ngày không hợp lệ. nhập theo định dạng dd-MM-yyyy.");
            return null;
        }
    }

    public static Double parseDouble(String input, double min, double max) {
        try {
            double value = Double.parseDouble(input.trim());
            if(value >= min && value <= max) {
                return value;
            }
            System.out.println("Lỗi: Giá trị phải nằm trong khoảng từ '" + min + " đến " + max + "'");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Hãy nhập một số hợp lệ.");
            return null;
        }
    }

    public static String validateStudentId (String input) {
        String studentId = input.trim();
        if (studentId.isEmpty()) {
            System.out.println("Lỗi: Mã sinh viên không được để trống");
            return null;
        }
        if (studentId.length() != Constants.STUDENT_ID_LENGTH) {
            System.out.println("Lỗi: Mã sinh viên phải có đúng '" + Constants.STUDENT_ID_LENGTH + "' ký tự.");
        }
        return studentId;
    }

    public static String validateAddress (String input) {
        String address = input.trim();
        if (address.length() >= Constants.MAX_ADDRESS_LENGTH) {
            System.out.println("Lỗi: Địa chỉ không được lớn hơn '" +  Constants.MAX_ADDRESS_LENGTH + "' ký tự.");
            return null;
        }
        return address;
    }

    public static String validateUniversityName (String input) {
        String universityName = input.trim();
        if (universityName.isEmpty() ||  universityName.length() >= Constants.MAX_UNIVERSITY_NAME_LENGTH) {
            System.out.println("Lỗi: Tên trường không được để trống và phải ít hơn '" + Constants.MAX_UNIVERSITY_NAME_LENGTH + "' ký tự.");
            return null;
        }
        return universityName;
    }

    public static Integer parseStartYear (String input) {
        try {
            int startYear = Integer.parseInt(input.trim());
            if (startYear >= Constants.MIN_BIRTH_YEAR && startYear <= LocalDate.now().getYear()) {
                return startYear;
            }
            System.out.println("Lỗi: Năm nhập học phải từ '" + Constants.MIN_START_YEAR + "' và không lớn hơn năm hiện tại.");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Hãy nhập một năm hợp lệ.");
            return null;
        }
    }
}
