package ir.ac.kntu.db_project_backend.models;

public class CommentType {

    private String commentType;

    private int id;

    public CommentType() {
    }

    public CommentType(int id, String commentType) {
        this.id = id;
        this.commentType = commentType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getCommentType() {
        return commentType;
    }
}
