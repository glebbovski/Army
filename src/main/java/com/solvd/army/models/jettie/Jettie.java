package com.solvd.army.models.jettie;

import java.util.Objects;

public class Jettie {
    private int id;
    private int numberOfShips;
    private int armyId;

    public Jettie() {
    }

    public Jettie(int id, int numberOfShips, int armyId) {
        this.id = id;
        this.numberOfShips = numberOfShips;
        armyId = armyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
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
        Jettie Jettie = (Jettie) o;
        return id == Jettie.id && numberOfShips == Jettie.numberOfShips && armyId == Jettie.armyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfShips, armyId);
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
