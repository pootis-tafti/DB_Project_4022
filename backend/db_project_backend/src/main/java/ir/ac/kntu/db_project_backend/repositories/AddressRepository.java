package ir.ac.kntu.db_project_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import ir.ac.kntu.db_project_backend.mappers.AddressRowMapper;
import ir.ac.kntu.db_project_backend.models.Address;

public class AddressRepository {
    
    @Autowired
    private JdbcTemplate template;

    private final String MAIN_QUERY = "SELECT * FROM addresses AS A JOIN cities as C JOIN provinces AS P ON P.id = C.province_id AND A.CID = C.id LIMIT 100";

    private final AddressRowMapper ROW_MAPPER = new AddressRowMapper();

    public Address findByBusinessId(int id){
        String sql = MAIN_QUERY + "WHERE BID = ?";
        return template.queryForObject(sql, ROW_MAPPER,id);
    }

    public void addAddress(Address address,int BID){
        String sql = "INSERT INTO addresses (BID,CID,Address) VALUES"
            +"(?,?,?,?)";
        template.update(sql, BID, address.getCity().getCityId(), address.getAddress());
    }

    public void updateAddress(Address address,int BID){
        String sql = "UPDATE Addresses SET CID = ?, Address = ? WHERE BID = ?";
        template.update(sql, address.getCity().getCityId(), address.getAddress(), BID);
    }
}
