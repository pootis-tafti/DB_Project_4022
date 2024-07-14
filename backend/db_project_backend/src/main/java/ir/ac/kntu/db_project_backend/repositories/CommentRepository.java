package ir.ac.kntu.db_project_backend.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ir.ac.kntu.db_project_backend.mappers.CommentRowMapper;
import ir.ac.kntu.db_project_backend.models.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private JdbcTemplate template;

    private final CommentRowMapper ROW_MAPPER = new CommentRowMapper();

    private final String MAIN_QUERY = "SELECT * FROM comments join type on type.ID = comments.Type ";

    public void addComment(Comment comment, int ADDID){
        String sql = "INSERT INTO Comments (ADDID,AID, Type, Description) VALUES "
            + " (?,?,?,?)";
        template.update(sql, ADDID, comment.getAccountId(),comment.getType().getId(), comment.getDescription());
    }

    public List<Comment> findByAd(int ADDID){
        return template.query(MAIN_QUERY + " WHERE ADDID = ?", ROW_MAPPER, ADDID);
    }
}
