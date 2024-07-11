package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.Comment;

public class CommentRowMapper implements RowMapper<Comment>{
    
    @Override
    @Nullable
    public Comment mapRow(@Nullable ResultSet rs, int rowNum) throws SQLException {
        if(rs == null) return null;
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("ADDID"));
        comment.setDescription(rs.getString("Description"));
        comment.setType(new CommentTypeRowMapper().mapRow(rs, rowNum));
        return comment;
    }
}
