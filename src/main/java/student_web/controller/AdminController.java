package student_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /** Trang chủ quản trị */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    /** Quản lý điểm ngưỡng */
    @GetMapping("/threshold")
    public String thresholdManage() {
        return "admin/threshold-manage";
    }

    /** Danh sách thí sinh */
    @GetMapping("/candidates")
    public String candidateList() {
        return "admin/candidate-list";
    }
}
