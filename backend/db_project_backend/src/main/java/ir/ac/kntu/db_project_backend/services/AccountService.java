package ir.ac.kntu.db_project_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import ir.ac.kntu.db_project_backend.models.Account;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Caching(evict = {
        @CacheEvict(value = "accounts", key = "#id"),
        @CacheEvict(value = "accountsByFirstName", allEntries = true),
        @CacheEvict(value = "accountsByLastName", allEntries = true),
        @CacheEvict(value = "accountsByCity", allEntries = true)
    })
    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    @Caching(evict = {
        @CacheEvict(value = "accounts", allEntries = true),
        @CacheEvict(value = "accountsByFirstName", allEntries = true),
        @CacheEvict(value = "accountsByLastName", allEntries = true),
        @CacheEvict(value = "accountsByCity", allEntries = true)
    })
    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }

    @Caching(evict = {
        @CacheEvict(value = "accounts", key = "#id"),
        @CacheEvict(value = "accountsByFirstName", allEntries = true),
        @CacheEvict(value = "accountsByLastName", allEntries = true),
        @CacheEvict(value = "accountsByCity", allEntries = true)
    })
    public void deleteAccount(int id) {
        accountRepository.deleteAccount(id);
    }

    @Caching(evict = {
        @CacheEvict(value = "accounts", key = "#id"),
        @CacheEvict(value = "accountsByFirstName", allEntries = true),
        @CacheEvict(value = "accountsByLastName", allEntries = true),
        @CacheEvict(value = "accountsByCity", allEntries = true)
    })
    public void disableAccount(int id) {
        accountRepository.disableAccount(id);
    }

    @Caching(evict = {
        @CacheEvict(value = "accounts", key = "#id"),
        @CacheEvict(value = "accountsByFirstName", allEntries = true),
        @CacheEvict(value = "accountsByLastName", allEntries = true),
        @CacheEvict(value = "accountsByCity", allEntries = true)
    })
    public void enableAccount(int id) {
        accountRepository.enableAccount(id);
    }

    @Cacheable(value = "accountsByFirstName", key = "#firstName")
    public List<Account> findByFirstName(String firstName) {
        return accountRepository.findByFirstName(firstName);
    }

    @Cacheable(value = "accountsByLastName", key = "#lastName")
    public List<Account> findByLastName(String lastName) {
        return accountRepository.findByLastName(lastName);
    }

    @Cacheable(value = "accountsByCity", key = "#city")
    public List<Account> findByCity(City city) {
        return accountRepository.findByCity(city);
    }

    @Cacheable(value = "accounts", key = "#id")
    public Account findById(int id) {
        return accountRepository.findById(id);
    }

    public boolean existsByEmail(String email){
        return accountRepository.existsByEmail(email);
    }
}
