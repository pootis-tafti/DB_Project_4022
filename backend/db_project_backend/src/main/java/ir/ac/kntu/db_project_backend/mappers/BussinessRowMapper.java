package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Bussiness;

public class BussinessRowMapper implements RowMapper<Bussiness> {
    @Override
    @Nullable
    public Bussiness mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Bussiness bussiness = new Bussiness();
        bussiness.setId(rs.getInt("BID"));
        bussiness.setName(rs.getString("Name"));
        bussiness.setOwnerId(rs.getInt("OID"));
        bussiness.setSerialNumber(rs.getString("SerialNUMBER"));
        bussiness.setType(rs.getString("Type"));
        bussiness.setAddress(new AddressRowMapper().mapRow(rs, rowNum));
        return bussiness;
    }
}
