package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.models.Account;
import ir.ac.kntu.db_project_backend.mappers.AccountRowMapper;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private AccountRowMapper rowMapper;

    private final String mainquery = "SELECT A.* , C.name ,  FROM Accounts AS A JOIN cITIES AS C JOIN provinces ON C.id = A.ActiveCity ";

    public void updateAccount(){

    }

    public void createAccount(){

    }

    public void deleteAccount(int id){}

    public List<Account> findByFirstName(String fistName){
        String sql = "";
        return template.query(sql,rowMapper, fistName);
    }

    public List<Account> findByLastName(String lastName){
        String sql = "";
        return template.query(sql,rowMapper, lastName);
    }

    public List<Account> findByCities(String... cities){
        String sql = "   ActiveCity = " + cities[0];
        for (int i = 1; i < cities.length; i++) {
            sql += " OR ActiveCity = " + "";
        }
        return template.query(sql,rowMapper);
    }

    public Account findById(int id){
        String sql = "";
        return template.queryForObject(sql, rowMapper);
    }
    
}
