package com.solvd.army.models.jettie;

import java.util.Objects;

public class Jettie {
    private int id;
    private int numberOfShips;
    private int Army_id;

    public Jettie() {
    }

    public Jettie(int id, int numberOfShips, int army_id) {
        this.id = id;
        this.numberOfShips = numberOfShips;
        Army_id = army_id;
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
        Jettie Jettie = (Jettie) o;
        return id == Jettie.id && numberOfShips == Jettie.numberOfShips && Army_id == Jettie.Army_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfShips, Army_id);
    }

    @Override
    public String toString() {
        return "Jettie{" +
                "id=" + id +
                ", numberOfShips=" + numberOfShips +
                ", Army_id=" + Army_id +
                '}';
    }
}
