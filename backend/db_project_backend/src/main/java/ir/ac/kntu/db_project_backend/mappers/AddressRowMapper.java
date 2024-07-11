package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Address;

public class AddressRowMapper implements RowMapper<Address>{
    @Override
    @Nullable
    public Address mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Address address = new Address();
        address.setAddress(rs.getString("Address"));
        address.setCity(new CityRowMapper().mapRow(rs, rowNum));
        return address;
    }
}
