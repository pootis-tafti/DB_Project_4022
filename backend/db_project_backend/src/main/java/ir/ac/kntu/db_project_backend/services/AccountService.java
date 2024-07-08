package ir.ac.kntu.db_project_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.repositories.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository repository;

    
}
