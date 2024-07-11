package ir.ac.kntu.db_project_backend.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.ProvinceRowMapper;
import ir.ac.kntu.db_project_backend.models.Province;

@Repository
public class ProvinceRepository {
    
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private ProvinceRowMapper rowMapper;

    private final String MAIN_QUERY = "SELECT * FROM provinces ";
    
    public List<Province> findAll(){
        return template.query(MAIN_QUERY, rowMapper);
    }

    public Province findById(int id){
        return template.queryForObject(MAIN_QUERY + "WHERE id = ?", rowMapper,id);
    }
}
