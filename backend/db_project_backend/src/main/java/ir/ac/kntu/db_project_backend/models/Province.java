package ir.ac.kntu.db_project_backend.models;

public class Province {

    public Province() {
        super();
    }
    
    private int provinceId;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getProvinceId() {
        return provinceId;
    }
}
