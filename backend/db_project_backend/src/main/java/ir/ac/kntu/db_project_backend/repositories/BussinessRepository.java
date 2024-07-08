package ir.ac.kntu.db_project_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BussinessRepository {
    
    @Autowired
    private JdbcTemplate template;
    
}
