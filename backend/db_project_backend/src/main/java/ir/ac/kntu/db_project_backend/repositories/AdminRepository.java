package ir.ac.kntu.db_project_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate template;

    public boolean existsById(int accountId) {
        Integer count = template.queryForObject("SELECT COUNT(*) FROM AdminsWHERE AID = ?", Integer.class, accountId);
        return count > 0;
    }

    public boolean existsByEmail(String email) {
        Integer count = template.queryForObject("SELECT COUNT(*) FROM Admins AS H Join Account as A on A.AID = H.AID  WHERE Email = ?", Integer.class, email);
        return count > 0;
    }
}

