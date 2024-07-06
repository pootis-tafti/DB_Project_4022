package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import ir.ac.kntu.db_project_backend.models.Account;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    @NonNull
    public Account mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("AID"));
        account.setEmail(rs.getNString("Email"));
        account.setPhoneNumber(rs.getString("Phone"));
        account.setActiveCity(rs.getString("ActiveCity"));
        account.setActiveProvince(rs.getString("ActiveProvince"));
        return account;
    }
}
