package com.solvd.army.models.barrack;

import java.util.Objects;

public class Barrack {
    private long id = -1;
    private int numberOfBeds;
    private int numberOfFloors;
    private long armyId;

    public Barrack() {
    }

    public Barrack(int numberOfBeds, int numberOfFloors, long armyId) {
        this.numberOfBeds = numberOfBeds;
        this.numberOfFloors = numberOfFloors;
        this.armyId = armyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getArmyId() {
        return armyId;
    }

    public void setArmyId(long armyId) {
        this.armyId = armyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrack Barrack = (Barrack) o;
        return numberOfBeds == Barrack.numberOfBeds
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
