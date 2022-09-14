package com.solvd.army.models.abstractClasses;

import java.sql.Date;
import java.util.Objects;

public abstract class Ship {
    private int id;
    private String name;
    private Date releaseDate;
    private int strength;
    private int jettiesId;

    public Ship() {
    }

    public Ship(int id, String name, Date releaseDate, int strength, int jettiesId) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.strength = strength;
        jettiesId = jettiesId;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getJettiesId() {
        return jettiesId;
    }

    public void setJettiesId(int jettiesId) {
        jettiesId = jettiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return id == ship.id && strength == ship.strength && jettiesId == ship.jettiesId
                && Objects.equals(name, ship.name) && Objects.equals(releaseDate, ship.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, strength, jettiesId);
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
