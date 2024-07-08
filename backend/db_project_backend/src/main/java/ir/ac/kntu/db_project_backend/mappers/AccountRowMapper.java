package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.micrometer.common.lang.Nullable;
import ir.ac.kntu.db_project_backend.models.Account;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    @Nullable
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("AID"));
        account.setEmail(rs.getNString("Email"));
        account.setPhoneNumber(rs.getString("Phone"));
        account.setActiveCity(rs.getString("ActiveCity"));
        account.setActiveProvince(rs.getString("ActiveProvince"));
        account.setStatus(rs.getBoolean("Status"));
        return account;
    }
}
