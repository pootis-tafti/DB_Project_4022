package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.lang.NonNull;

import ir.ac.kntu.db_project_backend.models.Admin;

public class AdminRowMapper extends AccountRowMapper {
    @Override
    @NonNull
    public Admin mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return (Admin) super.mapRow(rs, rowNum);
    }
}
