package student_web.model;

public class Student {
    private String cccd;
    private String birth;

    public Student(String cccd, String birth) {
        this.cccd = cccd;
        this.birth = birth;
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
}
