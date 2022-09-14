package com.solvd.army.models.barrack;

import java.util.Objects;

public class Barrack {
    private int id;
    private int numberOfBeds;
    private int numberOfFloors;
    private int armyId;

    public Barrack() {
    }

    public Barrack(int id, int numberOfBeds, int numberOfFloors, int armyId) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
        this.numberOfFloors = numberOfFloors;
        armyId = armyId;
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

    public int getArmyId() {
        return armyId;
    }

    public void setArmyId(int armyId) {
        armyId = armyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrack Barrack = (Barrack) o;
        return id == Barrack.id && numberOfBeds == Barrack.numberOfBeds
                && numberOfFloors == Barrack.numberOfFloors && armyId == Barrack.armyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfBeds, numberOfFloors, armyId);
    }

    @Override
    public String toString() {
        return "Barrack{" +
                "id=" + id +
                ", numberOfBeds=" + numberOfBeds +
                ", numberOfFloors=" + numberOfFloors +
                ", armyId=" + armyId +
                '}';
    }
}
