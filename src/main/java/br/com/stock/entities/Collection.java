package br.com.stock.entities;

import java.util.List;

public class Collection {

    private String name;
    private String description;
    private List<String> keyword;

    public Collection(){

    }

    public Collection(String name, String description, List<String> keyword) {
        this.name = name;
        this.description = description;
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", keyword=" + keyword.toString() +
                '}';
    }
}
