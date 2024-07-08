package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Advertisment;

public class AdvertismentRowMapper implements RowMapper<Advertisment> {
    @Override
    @Nullable
    public Advertisment mapRow( ResultSet rs, int rowNum) throws SQLException {
        Advertisment advertisment = new Advertisment();
        advertisment.setDate(rs.getDate("DateModified"));
        advertisment.setId(rs.getInt("ADDID"));
        advertisment.setDescription(rs.getString("Description"));
        advertisment.setPrice(rs.getInt("Price"));
        advertisment.setTitle(rs.getString("Title"));
        advertisment.setIsNew(rs.getBoolean("IsNew"));
        return advertisment;
    }
}
