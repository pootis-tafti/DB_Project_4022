package ir.ac.kntu.db_project_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsById(int accountId) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM AdminsWHERE AID = ?", Integer.class, accountId);
        return count > 0;
    }
}

