package ir.ac.kntu.db_project_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ir.ac.kntu.db_project_backend.services.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/login/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("admin/{email}")
    public String adminlogin(@RequestParam String email, @RequestParam String otp) { 
        return loginService.adminlogin(email) ? "sent otp" : "Login failed";
    }

    @PostMapping("user/{email}")
    public String login(@RequestParam String email) {
        return loginService.userlogin(email) ? "sent otp" : "Login failed";
    }

    @PostMapping("/confirm/{otp}")
    public String ValidateOtp(@RequestParam String email) {
        return loginService.confirmOTP(email, email) ? "Login successful" : "Login failed";
    }


}
