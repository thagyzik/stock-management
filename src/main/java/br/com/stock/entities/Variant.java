package br.com.stock.entities;

public class Variant {

    private String color;
    private String size;
    private String gender;
    private String sleeve;
    private String activity;
    private String material;

    public Variant(){

    }

    public Variant(String color, String size, String gender, String sleeve, String activity, String material) {
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.sleeve = sleeve;
        this.activity = activity;
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", sleeve='" + sleeve + '\'' +
                ", activity='" + activity + '\'' +
                ", material='" + material + '\'' +
                '}';
    }

}
