package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.CommentTypeRowMapper;
import ir.ac.kntu.db_project_backend.models.CommentType;

@Repository
public class CommentTypeRepository {
    
    @Autowired
    JdbcTemplate template;

    private final CommentTypeRowMapper ROW_MAPPER = new CommentTypeRowMapper();

    public List<CommentType> findAll(){
        return template.query("SELECT * FROM Type", ROW_MAPPER);
    }
}
