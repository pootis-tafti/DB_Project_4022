package ir.ac.kntu.db_project_backend.models;

public class Comment {

    private int CommentId;

    private String type;

    private String description;

    public Comment() {
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentId() {
        return CommentId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    
}
