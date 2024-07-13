package ir.ac.kntu.db_project_backend.models;

public class Image {
    private int ADDID;

    private String url;

    public void setADDID(int aDDID) {
        ADDID = aDDID;
    }

    public int getADDID() {
        return ADDID;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
