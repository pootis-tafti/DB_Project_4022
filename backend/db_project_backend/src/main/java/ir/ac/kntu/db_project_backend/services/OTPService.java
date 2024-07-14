package ir.ac.kntu.db_project_backend.services;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.repositories.RedisRepository;

@Service
public class OTPService {
    
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisRepository repository;

    private final Random OTP_GENERATOR = new Random();

    private final int OTP_DIGITS = 6;

    private final int OTP_EXPIRATION_TIME_MINUTES = 3;

    public boolean sendOTP(String email){
        try {
            int otp = 0;
            for (int i = 0; i < OTP_DIGITS; i++) {
                otp = otp * 10 + OTP_GENERATOR.nextInt(10);
            }
            mailService.sendSimpleMessage(email, "Your One Time Password For MarketPlace"
                ,String.format("Your password is %d", otp));
            repository.save(email, String.valueOf(otp), OTP_EXPIRATION_TIME_MINUTES, TimeUnit.MINUTES);
            return true;
        } catch (MailException exception) {
            return false;
        }
    }

    public boolean confirmOTP(String email, String otp){
        try {
            String expectedOTP = repository.find(email);
            return otp.equals(expectedOTP);
        } catch (Exception e) {
            return false;
        }
    }
}
