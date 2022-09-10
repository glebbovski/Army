package com.solvd.army.models.hangar;

import com.solvd.army.models.abstractClasses.HangarMilitary;

import java.util.Date;
import java.util.Objects;

public class InfantryFightingVehicles extends HangarMilitary {
    private int numberOfGuns;

    public InfantryFightingVehicles() {
        super();
    }

    public InfantryFightingVehicles(int id, String name, Date releaseDate, int strength,
                                    int hangars_id, int numberOfGuns) {
        super(id, name, releaseDate, strength, hangars_id);
        this.numberOfGuns = numberOfGuns;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InfantryFightingVehicles that = (InfantryFightingVehicles) o;
        return numberOfGuns == that.numberOfGuns;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfGuns);
    }

    @Override
    public String toString() {
        return super.toString() + "InfantryFightingVehicles{" +
                "numberOfGuns=" + numberOfGuns +
                "}}";
    }
}
