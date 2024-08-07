package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.CityRowMapper;
import ir.ac.kntu.db_project_backend.models.City;

@Repository
public class CityRepository {

    @Autowired
    private JdbcTemplate template;

    private final  CityRowMapper ROW_MAPPER = new CityRowMapper();

    private final String MAIN_QUERY = " SELECT * FROM cities AS C JOIN provinces AS P ON C.province_id = p.id ";
    
    public List<City> findAll(){
        return template.query(MAIN_QUERY, ROW_MAPPER);
    }

    public List<City> findByProvince(int provinceId){
        return template.query(MAIN_QUERY + " WHERE province_id = ?" + provinceId, ROW_MAPPER);
    }

    public List<City> findByName(String name){
        return template.query(MAIN_QUERY + " WHERE name = ?",ROW_MAPPER, name );
    }

    public City findById(int id){
        return template.queryForObject(MAIN_QUERY + " WHERE id = ?", ROW_MAPPER,id);
    }
}
