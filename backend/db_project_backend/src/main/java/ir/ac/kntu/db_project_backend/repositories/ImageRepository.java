package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.ImageRowMapper;
import ir.ac.kntu.db_project_backend.models.Image;

@Repository
public class ImageRepository {
    
    @Autowired
    private JdbcTemplate template;

    private final ImageRowMapper ROW_MAPPER = new ImageRowMapper();

    private final String MAIN_QUERY = "SELECT * FROM Images";

    public List<Image> findById(int id){
        return template.query(MAIN_QUERY + "WHERE id = ?", ROW_MAPPER,id);
    }
}
