package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Image;

public class ImageRowMapper implements RowMapper<Image>{
    @Override
    @Nullable
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Image image = new Image();
        image.setADDID(rs.getInt("ADDID"));
        image.setUrl(rs.getString("ImageLink"));
        return image;
    }
}
