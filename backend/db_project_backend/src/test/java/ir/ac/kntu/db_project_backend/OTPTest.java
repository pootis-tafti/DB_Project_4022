package ir.ac.kntu.db_project_backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ir.ac.kntu.db_project_backend.services.OTPService;

@SpringBootTest
public class OTPTest {
    
    @Autowired
    private OTPService service;

    @Test
    public void emailTest(){
        assertTrue(service.sendOTP("the.real.actual.lordpi@gmail.com"));
    }

    @Test
    public void confirmationTest(){
        assertTrue(service.confirmOTP("the.real.actual.lordpi@gmail.com", "843623"));
    }
}
