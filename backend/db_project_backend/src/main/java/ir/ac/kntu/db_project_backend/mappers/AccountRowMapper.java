package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Account;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    @Nullable
    public Account mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Account account = new Account();
        account.setId(rs.getInt("AID"));
        account.setFirstName(rs.getString("FirstName"));
        account.setLastName(rs.getString("LastName"));
        account.setEmail(rs.getNString("Email"));
        account.setPhoneNumber(rs.getString("Phone"));
        account.setActiveCity(new CityRowMapper().mapRow(rs, rowNum));
        account.setStatus(rs.getBoolean("Status"));
        return account;
    }
}
