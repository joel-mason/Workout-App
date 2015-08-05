package com.company.joeliomason.projectme.POJOs;

import java.io.Serializable;

/**
 * Created by joelmason on 01/04/2015.
 */
public class Set implements Serializable{

    public long id;
    public String name;
    public double weight;
    public int reps;
    public String date;
    public int category;

    public Set(long id, String name, double weight, int reps, String date, int category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.date = date;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public int getReps() {
        return reps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String toString()
    {
        return "name: " + name + ", weight: " + weight + ", reps: " + reps + ", date: " + date + ", category: " + category;
    }
}
