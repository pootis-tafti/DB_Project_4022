package ir.ac.kntu.db_project_backend.controllers;

import ir.ac.kntu.db_project_backend.models.Account;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable int id, @RequestBody Account account) {
        account.setId(id);
        accountService.updateAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<Void> disableAccount(@PathVariable int id) {
        accountService.disableAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/enable")
    public ResponseEntity<Void> enableAccount(@PathVariable int id) {
        accountService.enableAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<Account>> findByFirstName(@PathVariable String firstName) {
        List<Account> accounts = accountService.findByFirstName(firstName);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<Account>> findByLastName(@PathVariable String lastName) {
        List<Account> accounts = accountService.findByLastName(lastName);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<Account>> findByCity(@RequestBody City city) {
        List<Account> accounts = accountService.findByCity(city);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable int id) {
        Account account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
