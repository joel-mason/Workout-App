package com.company.joeliomason.projectme.POJOs;

/**
 * Created by JoelioMason on 04/03/15.
 */
public class Exercise{

    private String name;
    private int category;
    private int type;

    public Exercise(String name, int type, int category) {
        this.name = name;
        this.type = type;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Category" + category;
    }

}
