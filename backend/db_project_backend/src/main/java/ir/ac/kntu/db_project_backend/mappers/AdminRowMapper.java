package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;


import io.micrometer.common.lang.Nullable;
import ir.ac.kntu.db_project_backend.models.Admin;

public class AdminRowMapper extends AccountRowMapper {
    @Override
    @Nullable
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return (Admin) super.mapRow(rs, rowNum);
    }
}
