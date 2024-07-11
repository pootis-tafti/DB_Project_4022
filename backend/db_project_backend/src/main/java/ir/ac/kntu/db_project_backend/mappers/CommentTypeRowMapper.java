package ir.ac.kntu.db_project_backend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import ir.ac.kntu.db_project_backend.models.CommentType;

public class CommentTypeRowMapper implements RowMapper<CommentType> {
    @Override
    @Nullable
    public CommentType mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new CommentType(rs.getInt("ID"), rs.getString("Name"));
    }
}
