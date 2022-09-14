package com.solvd.army.models;

import java.util.Objects;

public class MainArmy {
    private long id = -1;
    private String name;
    private int rating;

    public MainArmy() {
    }

    public MainArmy(String name, int rating) {
        this.name = name;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainArmy mainArmy = (MainArmy) o;
        return rating == mainArmy.rating && Objects.equals(name, mainArmy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

    @Override
    public String toString() {
        return "MainArmy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
