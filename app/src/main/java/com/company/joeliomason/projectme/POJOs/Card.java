package com.company.joeliomason.projectme.POJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelmason on 09/04/2015.
 */
public class Card implements Serializable{
    public String id;
    public String name;
    public String date;
    List<Set> sets;

    public Card(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        sets = new ArrayList<>();
    }

    public Card() {}

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public List<Set> getSet() {
        return sets;
    }

    public void addSet(Set s) {
        sets.add(s);
    }

    public void setSets(List<Set> s) {
        sets = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString(){
        return"ID: " + id + ", Name: " + name + ", Set: " + sets.toString();
    }
}
