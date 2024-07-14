package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private AccountService accountService;
    
    public boolean adminlogin(String email){
        return adminService.existsByEmail(email) && otpService.sendOTP(email);
    }

    public boolean userlogin(String email){
        return accountService.existsByEmail(email) && otpService.sendOTP(email);
    }

    public boolean confirmOTP(String emailString, String otp){
        return otpService.confirmOTP(emailString, otp);
    }
}
