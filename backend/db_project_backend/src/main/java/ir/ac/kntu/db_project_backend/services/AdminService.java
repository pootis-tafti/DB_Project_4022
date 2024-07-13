package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.repositories.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean existsById(int accountId) {
        return adminRepository.existsById(accountId);
    }
}
