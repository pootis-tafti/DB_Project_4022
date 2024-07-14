package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.models.Account;
import ir.ac.kntu.db_project_backend.models.City;
import ir.ac.kntu.db_project_backend.mappers.AccountRowMapper;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate template;

    private final  AccountRowMapper ROW_MAPPER = new AccountRowMapper();

    private final String MAIN_QUERY = "SELECT * FROM Accounts AS A JOIN cities AS C JOIN provinces " 
        + "ON C.id = A.ActiveCity AND C.province_id = provinces.id";

    public void updateAccount(Account account){
        String sql = "UPDATE accounts SET FirstName =?," + 
            "LastName= ? , Phone= ? , Email= ? , ActiveCity= ? WHERE AID = ?";
        template.update(sql, account.getFirstName(), account.getLastName(),
             account.getPhoneNumber(), account.getEmail(), account.getActiveCity().getCityId(), account.getId());
    }

    public void createAccount(Account account){
        String sql = "INSERT INTO Accounts (FirstName, LastName, Phone, Email, ActiveCity) VALUES"
            +"(?, ?, ?, ?, ?)";
        template.update(sql, account.getFirstName(), account.getLastName(),
        account.getPhoneNumber(), account.getEmail(), account.getActiveCity().getCityId());
    }

    public void deleteAccount(int id){
        String sql = "DELETE FROM accounts WHERE AID = ?";
        template.update(sql, id);

    }

    public void disableAccount(int id){
        String sql = "UPDATE accounts SET Status = 0 WHERE AID = ?";
        template.update(sql, id);
    }

    public void enableAccount(int id){
        String sql = "UPDATE accounts SET Status = 1 WHERE AID = ?";
        template.update(sql, id);
    }

    public List<Account> findByFirstName(String firstName){
        String sql = MAIN_QUERY + "WHERE FirstName = ?";
        return template.query(sql,ROW_MAPPER, firstName);
    }

    public List<Account> findByLastName(String lastName){
        String sql = "WHERE LastName = ?";
        return template.query(sql,ROW_MAPPER, lastName);
    }

    public List<Account> findByCity(City city){
        String sql = MAIN_QUERY + " WHERE  ActiveCity = ?";
        return template.query(sql,ROW_MAPPER, city.getCityId());
    }

    public Account findById(int id){
        String sql = MAIN_QUERY + "WHERE AID = ?";
        return template.queryForObject(sql, ROW_MAPPER,id);
    }
    

    public boolean existsByEmail(String email) {
        Integer count = template.queryForObject("SELECT COUNT(*) FROM Account WHERE Email = ?", Integer.class, email);
        return count > 0;
    }
}
