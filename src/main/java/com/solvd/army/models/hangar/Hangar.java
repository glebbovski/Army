package com.solvd.army.models.hangar;

import java.util.Objects;

public class Hangar {
    private long id = -1;
    private int numberOfMilitaryCraft;
    private long armyId;

    public Hangar() {
    }

    public Hangar(int numberOfMilitaryCraft, long armyId) {
        this.numberOfMilitaryCraft = numberOfMilitaryCraft;
        this.armyId = armyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfMilitaryCraft() {
        return numberOfMilitaryCraft;
    }

    public void setNumberOfMilitaryCraft(int numberOfMilitaryCraft) {
        this.numberOfMilitaryCraft = numberOfMilitaryCraft;
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
        Hangar Hangar = (Hangar) o;
        return numberOfMilitaryCraft == Hangar.numberOfMilitaryCraft && armyId == Hangar.armyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfMilitaryCraft, armyId);
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
