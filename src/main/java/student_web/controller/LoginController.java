package student_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /** Trang chủ */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
