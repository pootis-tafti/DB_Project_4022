package ir.ac.kntu.db_project_backend.models;

import java.sql.Date;


public class Advertisment {

    private int id;

    private String title;
    
    private int price;

    private Date date;

    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
