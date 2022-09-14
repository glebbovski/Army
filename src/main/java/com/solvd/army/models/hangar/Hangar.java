package com.solvd.army.models.hangar;

import java.util.Objects;

public class Hangar {
    private int id;
    private int numberOfMilitaryCraft;
    private int armyId;

    public Hangar() {
    }

    public Hangar(int id, int numberOfMilitaryCraft, int armyId) {
        this.id = id;
        this.numberOfMilitaryCraft = numberOfMilitaryCraft;
        armyId = armyId;
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
        Hangar Hangar = (Hangar) o;
        return id == Hangar.id && numberOfMilitaryCraft == Hangar.numberOfMilitaryCraft && armyId == Hangar.armyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfMilitaryCraft, armyId);
    }

    @Override
    public String toString() {
        return "Hangar{" +
                "id=" + id +
                ", numberOfMilitaryCraft=" + numberOfMilitaryCraft +
                ", armyId=" + armyId +
                '}';
    }
}
