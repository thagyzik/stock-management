package br.com.stock.entities;

import java.util.List;

public class Product {

    private String name;
    private Double price;
    private String description;
    private Integer quantity;
    private Collection collection;
    private List<String> imagesList;
    private Variant variant;

    public Product(){

    }

    public Product(String name, Double price, String description, Integer quantity, Collection collection, List<String> imagesList, Variant variant) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.collection = collection;
        this.imagesList = imagesList;
        this.variant = variant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", collection=" + collection +
                ", imagesList=" + imagesList +
                ", variant=" + variant +
                '}';
    }

}
