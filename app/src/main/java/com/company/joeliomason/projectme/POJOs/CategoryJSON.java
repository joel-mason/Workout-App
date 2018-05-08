package com.company.joeliomason.projectme.POJOs;

/**
 * Created by joelmason on 07/03/2015.
 */
public class CategoryJSON {
    private String name;
    private long id;

    public CategoryJSON(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "Name: " + name + ", ID: " + id;
    }
}