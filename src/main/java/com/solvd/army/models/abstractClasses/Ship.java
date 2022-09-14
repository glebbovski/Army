package com.solvd.army.models.abstractClasses;

import java.sql.Date;
import java.util.Objects;

public abstract class Ship {
    private long id = -1;
    private String name;
    private java.sql.Date releaseDate;
    private int strength;
    private long jettiesId;

    public Ship() {
    }

    public Ship(String name, java.sql.Date releaseDate, int strength, long jettiesId) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.strength = strength;
        this.jettiesId = jettiesId;
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

    public java.sql.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public long getJettiesId() {
        return jettiesId;
    }

    public void setJettiesId(long jettiesId) {
        this.jettiesId = jettiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return strength == ship.strength && jettiesId == ship.jettiesId
                && Objects.equals(name, ship.name) && Objects.equals(releaseDate, ship.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, strength, jettiesId);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", strength=" + strength +
                ", jettiesId=" + jettiesId +
                ", ";
    }
}
