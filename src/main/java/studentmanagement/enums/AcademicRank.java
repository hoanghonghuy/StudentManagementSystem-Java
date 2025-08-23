package main.java.studentmanagement.enums;

public enum AcademicRank {
    POOR("Kém"),
    WEAK("Yếu"),
    AVERAGE("Trung bình"),
    GOOD("Khá"),
    VERY_GOOD("Giỏi"),
    EXCELLENT("Xuất sắc");

    private final String description;

    AcademicRank(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
