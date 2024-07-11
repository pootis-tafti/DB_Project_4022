package ir.ac.kntu.db_project_backend.models;

import java.sql.Date;


public class AddStatus {
    
    private boolean status;

    private Date date;

    private String adminNote;

    public AddStatus(boolean status, String adminNote, int addId, Date date) {
        this.status = status;
        this.adminNote = adminNote;
        this.date = date;
    }

    public AddStatus() {
        super();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public boolean getStatus(){
        return status;
    }

}
