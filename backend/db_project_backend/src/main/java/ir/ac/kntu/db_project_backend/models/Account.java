package ir.ac.kntu.db_project_backend.models;


public class Account {

    private int id;

    private String phoneNumber;

    private String email;

    private int activeCity;

    public void setId(int id) {
        this.id = id;
    }
    
    public void setActiveCity(int activeCity) {
        this.activeCity = activeCity;
    }

    public int getActiveCity() {
        return activeCity;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }
}
