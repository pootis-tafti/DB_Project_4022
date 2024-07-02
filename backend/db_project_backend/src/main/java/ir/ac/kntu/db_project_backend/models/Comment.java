package ir.ac.kntu.db_project_backend.models;


public class Comment {

    private int CommentId;

    private CommentType type;

    private String description;

    public Comment(CommentType type, String description) {
        this.type = type;
        this.description = description;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommentId() {
        return CommentId;
    }

    public CommentType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    
}
