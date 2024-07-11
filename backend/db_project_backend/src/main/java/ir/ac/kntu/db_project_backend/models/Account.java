package ir.ac.kntu.db_project_backend.models;


public class Account {

    private int id;

    private boolean status;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private City activeCity;

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setActiveCity(City activeCity) {
        this.activeCity = activeCity;
    }

    public City getActiveCity() {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getStatus(){
        return status;
    }
}
