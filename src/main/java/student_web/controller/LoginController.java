package student_web.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import student_web.model.Student;

@Controller
public class LoginController {
    private static ArrayList<Student> students = new ArrayList<>();
    static {
        students.add(new Student("TS_0001", "25072007"));
        students.add(new Student("TS_0004", "02082007"));
    }

    @GetMapping("/")
    public String Home() {
        return "home";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/result")
    public String Result() {
        return "result";
    }

    @PostMapping("/login")
    public String check(@ModelAttribute("Student") Student student, Model model,
            RedirectAttributes redirectAttributes) {
        String username = student.getCccd();
        String password = student.getBirth();

        if (!Pattern.matches("^TS_[0-9]{4}$", username)) {
            model.addAttribute("error", "Username wrong pattern");
            return "login";
        }

        for (Student stud : students) {
            if (username.equals(stud.getCccd()) && password.equals(stud.getBirth())) {
                redirectAttributes.addFlashAttribute("student", student);
                return "redirect:/result";
            }
        }

        model.addAttribute("error", "Username or Password wrong");
        return "login";
    }
}
