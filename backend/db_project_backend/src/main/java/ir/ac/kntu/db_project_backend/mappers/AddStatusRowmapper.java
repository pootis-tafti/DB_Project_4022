package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.AddStatus;

public class AddStatusRowmapper implements RowMapper<AddStatus>{
    @Override
    @Nullable
    public AddStatus mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        AddStatus addStatus = new AddStatus();
        addStatus.setAddId(rs.getInt("ADDID"));
        addStatus.setAdminNote(rs.getString("AdminNote"));
        addStatus.setStatus(rs.getBoolean("Status"));
        addStatus.setDate(rs.getDate("LastUpdated"));
        return addStatus;
    }

    
}