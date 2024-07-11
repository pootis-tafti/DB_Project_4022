package ir.ac.kntu.db_project_backend.models;

public class City {

    private int cityId;

    private String name;

    private Province province;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Province getProvince() {
        return province;
    }
}
