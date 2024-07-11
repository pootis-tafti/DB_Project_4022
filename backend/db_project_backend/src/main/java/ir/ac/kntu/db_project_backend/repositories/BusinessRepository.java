package ir.ac.kntu.db_project_backend.repositories;

import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.BusinessRowMapper;
import ir.ac.kntu.db_project_backend.models.Business;
import ir.ac.kntu.db_project_backend.models.City;

@Repository
public class BusinessRepository {
    
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private AddressRepository addressRepository;

    private final BusinessRowMapper ROW_MAPPER = new BusinessRowMapper();

    private final String MAIN_QUERY = "SELECT * FROM "
        + " businesses AS B JOIN addresses as A JOIN cities As C JOIN provinces as P "
        + "ON P.id = C.province_id AND B.BID = A.BID and A.CID = C.id";


    public List<Business> findAll(){
        return template.query(MAIN_QUERY, ROW_MAPPER);
    }

    public List<Business> findByOwnerId(int id){
        return template.query(MAIN_QUERY + "WHERE OID = ?", ROW_MAPPER,id);
    }

    public List<Business> findByCity(City city){
        return template.query(MAIN_QUERY + "WHERE C.id = ? OR C.name = ?", 
            ROW_MAPPER,city.getCityId(),city.getName());
    }

    public List<Business> findByName(City city){
        return template.query(MAIN_QUERY + "WHERE C.id = ? OR C.name = ?", 
            ROW_MAPPER,city.getCityId(),city.getName());
    }

    public void updateBusiness(Business business){
        String sql = "UPDATE accounts SET Name = ?, SerialNumber = ?"
            + "Type = ? Where BID = ?";

        template.update(sql, business.getName(),business.getSerialNumber(),business.getType(),business.getId());
        addressRepository.updateAddress(business.getAddress(), business.getId());
    }

    public void addBusiness(Business business){
        String sql = "INSERT INTO Businesses (OID, Type, SerialNUMBER, Name) VALUES"
            +"(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, business.getOwnerId());
            ps.setString(2, business.getType());
            ps.setString(3, business.getSerialNumber());
            ps.setString(4, business.getName());
            return ps;
        }, keyHolder);
        addressRepository.addAddress(business.getAddress(), keyHolder.getKey().intValue());
    }

    
    
}
