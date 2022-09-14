package com.solvd.army.models.jettie;

import java.util.Objects;

public class Jettie {
    private long id = -1;
    private int numberOfShips;
    private long armyId;

    public Jettie() {
    }

    public Jettie(int numberOfShips, long armyId) {
        this.numberOfShips = numberOfShips;
        this.armyId = armyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
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
        Jettie Jettie = (Jettie) o;
        return numberOfShips == Jettie.numberOfShips && armyId == Jettie.armyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfShips, armyId);
    }

    @Override
    public String toString() {
        return "Jettie{" +
                "id=" + id +
                ", numberOfShips=" + numberOfShips +
                ", armyId=" + armyId +
                '}';
    }
}
