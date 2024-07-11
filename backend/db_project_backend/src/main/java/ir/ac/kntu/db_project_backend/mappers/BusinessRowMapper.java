package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Business;

public class BusinessRowMapper implements RowMapper<Business> {
    @Override
    @Nullable
    public Business mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Business business = new Business();
        business.setId(rs.getInt("BID"));
        business.setName(rs.getString("Name"));
        business.setOwnerId(rs.getInt("OID"));
        business.setSerialNumber(rs.getString("SerialNUMBER"));
        business.setType(rs.getString("Type"));
        business.setAddress(new AddressRowMapper().mapRow(rs, rowNum));
        return business;
    }
}
