package com.solvd.army.models.hangar;

import java.util.Objects;

public class Hangar {
    private int id;
    private int numberOfMilitaryCraft;
    private int Army_id;

    public Hangar() {
    }

    public Hangar(int id, int numberOfMilitaryCraft, int army_id) {
        this.id = id;
        this.numberOfMilitaryCraft = numberOfMilitaryCraft;
        Army_id = army_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfMilitaryCraft() {
        return numberOfMilitaryCraft;
    }

    public void setNumberOfMilitaryCraft(int numberOfMilitaryCraft) {
        this.numberOfMilitaryCraft = numberOfMilitaryCraft;
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
        Hangar Hangar = (Hangar) o;
        return id == Hangar.id && numberOfMilitaryCraft == Hangar.numberOfMilitaryCraft && Army_id == Hangar.Army_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfMilitaryCraft, Army_id);
    }

    @Override
    public String toString() {
        return "Hangar{" +
                "id=" + id +
                ", numberOfMilitaryCraft=" + numberOfMilitaryCraft +
                ", Army_id=" + Army_id +
                '}';
    }
}
