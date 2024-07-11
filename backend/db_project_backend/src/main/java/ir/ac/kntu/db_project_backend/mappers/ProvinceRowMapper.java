package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Province;

public class ProvinceRowMapper implements RowMapper<Province>{
    @Override
    @Nullable
    public Province mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Province province = new Province();
        province.setName(rs.getString("provinces.name"));
        province.setProvinceId(rs.getInt("provinces.id"));
        return province;
    }
}
