package student_web.model;

public class Student {
    private String cccd;
    private String birth;
    private String hoTen;

    /** Default constructor (cần cho Thymeleaf form binding) */
    public Student() {
    }

    public Student(String cccd, String birth) {
        this.cccd = cccd;
        this.birth = birth;
    }

    public Student(String cccd, String birth, String hoTen) {
        this.cccd = cccd;
        this.birth = birth;
        this.hoTen = hoTen;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCccd() {
        return cccd;
    }

    public String getBirth() {
        return birth;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
