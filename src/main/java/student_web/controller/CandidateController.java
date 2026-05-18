package student_web.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import student_web.model.Student;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    // Dữ liệu mẫu (sẽ thay bằng Repository/Service sau)
    private static ArrayList<Student> students = new ArrayList<>();
    static {
        students.add(new Student("TS_0001", "25072007"));
        students.add(new Student("TS_0004", "02082007"));
    }

    /** Trang đăng nhập tra cứu */
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("student", new Student());
        return "candidate/lookup-login";
    }

    /** Xử lý đăng nhập */
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("student") Student student, Model model,
                               RedirectAttributes redirectAttributes) {
        String username = student.getCccd();
        String password = student.getBirth();

        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập CCCD.");
            return "candidate/lookup-login";
        }

        if (password == null || !Pattern.matches("^\\d{8}$", password)) {
            model.addAttribute("error", "Ngày sinh phải đúng 8 chữ số (ddmmyyyy).");
            return "candidate/lookup-login";
        }

        for (Student stud : students) {
            if (username.equals(stud.getCccd()) && password.equals(stud.getBirth())) {
                redirectAttributes.addFlashAttribute("student", student);
                return "redirect:/candidate/result";
            }
        }

        model.addAttribute("error", "CCCD hoặc ngày sinh không đúng. Vui lòng thử lại.");
        return "candidate/lookup-login";
    }

    /** Trang kết quả tuyển sinh */
    @GetMapping("/result")
    public String resultPage() {
        return "candidate/lookup-result";
    }

    /** Công cụ tính điểm ĐGNL */
    @GetMapping("/calc-dgnl")
    public String calcDgnlPage() {
        return "candidate/calc-dgnl";
    }

    /** Công cụ tính điểm VSAT */
    @GetMapping("/calc-vsat")
    public String calcVsatPage() {
        return "candidate/calc-vsat";
    }
}
