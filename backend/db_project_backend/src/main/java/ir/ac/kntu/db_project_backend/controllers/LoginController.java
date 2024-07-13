package ir.ac.kntu.db_project_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ir.ac.kntu.db_project_backend.services.OTPService;

@RestController
public class LoginController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private 

    @PostMapping("/login/admin/{email}/{otp}")
    public String adminlogin(@RequestParam String email, @RequestParam String otp) {
        // Handle login logic here, return JWT or session token
        return "Login successful";
    }

    @PostMapping("/login/useer")
    public String login(@RequestParam String username, String otp) {
        // Handle login logic here, return JWT or session token
        return "Login successful";
    }

    @PostMapping("/otp")
    public String generateOtp(@RequestParam String username) {
        return otpService.generateOTP(username);
    }


}
