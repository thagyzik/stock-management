package br.com.stock.entities;

public class Images {

    private String name;
    private String size;
    private String extension;
    private String path;

    public Images(){

    }

    public Images(String name, String size, String extension, String path) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Images{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", extension='" + extension + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

}
