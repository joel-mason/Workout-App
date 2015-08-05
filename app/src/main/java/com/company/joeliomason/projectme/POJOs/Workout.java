package com.company.joeliomason.projectme.POJOs;

/**
 * Created by JoelioMason on 04/03/15.
 */
public class Workout {

    private String name;
    private long category;
    private int type;

    public Workout(String name, int type, long category) {
        this.name = name;
        this.type = type;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public long getCategory() {
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
}
