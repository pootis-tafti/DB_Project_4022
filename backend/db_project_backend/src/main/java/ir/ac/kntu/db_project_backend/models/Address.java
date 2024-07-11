package ir.ac.kntu.db_project_backend.models;

public class Address {
    private String address;

    private City city;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }
}
