package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.City;

public class CityRowMapper implements RowMapper<City>{
    @Override
    @Nullable
    public City mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        City city = new City();
        city.setCityId(rs.getInt("id"));
        city.setProvince(new ProvinceRowMapper().mapRow(rs, rowNum));
        city.setName(rs.getString("name"));
        return city;
    }
    
}