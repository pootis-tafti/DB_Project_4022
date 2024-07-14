package ir.ac.kntu.db_project_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.AddStatusRowmapper;
import ir.ac.kntu.db_project_backend.models.AddStatus;

@Repository
public class AddStatusRepository {

    @Autowired
    private JdbcTemplate template;

    private final AddStatusRowmapper ROW_MAPPER = new AddStatusRowmapper();

    private final String MAIN_QUERY = "SELECT * FROM AddStatus";

    public AddStatus findByAddId(int ADDID){
        return template.queryForObject(MAIN_QUERY + "WHERE ADDID = ?", ROW_MAPPER, ADDID);
    }

    public void resetStatus(int ADDID){
        String sql = "UPDATE AddStatus SET AdminNote = '' , Status = 0, LastUpdated = CURRENT_DATE() WHERE ADDID = ?";
        template.update(sql, ADDID);
    }

    public void createStatus(int ADDID){
        String sql = "INSERT INTO AddStatus (AdminNote, Status, LastUpdated, ADDID) "
            +"VALUES ('not answerd yet',0,CURRENT_DATE() , ?)";
        template.update(sql, ADDID);
    }

    public void delete(int ADDID){
        template.update("DELETE FROM AddStatus WHERE ADDID = ?", ADDID);
    }
}