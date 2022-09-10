package com.solvd.army.models.barrack;

import java.util.Objects;

public class Barrack {
    private int id;
    private int numberOfBeds;
    private int numberOfFloors;
    private int Army_id;

    public Barrack() {
    }

    public Barrack(int id, int numberOfBeds, int numberOfFloors, int army_id) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
        this.numberOfFloors = numberOfFloors;
        Army_id = army_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getArmy_id() {
        return Army_id;
    }

    public void setArmy_id(int army_id) {
        Army_id = army_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrack Barrack = (Barrack) o;
        return id == Barrack.id && numberOfBeds == Barrack.numberOfBeds
                && numberOfFloors == Barrack.numberOfFloors && Army_id == Barrack.Army_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfBeds, numberOfFloors, Army_id);
    }

    @Override
    public String toString() {
        return "Barrack{" +
                "id=" + id +
                ", numberOfBeds=" + numberOfBeds +
                ", numberOfFloors=" + numberOfFloors +
                ", Army_id=" + Army_id +
                '}';
    }
}
