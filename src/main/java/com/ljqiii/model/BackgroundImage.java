package com.ljqiii.model;

public class BackgroundImage {

    int id;

    String name;
    String filename;
    String description;


    public BackgroundImage() {
    }

    public BackgroundImage(String name, String filename, String description) {
        this.name = name;
        this.filename = filename;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
