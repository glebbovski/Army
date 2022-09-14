package com.solvd.army.models.hangar;

import com.solvd.army.models.abstractClasses.HangarMilitary;

import java.sql.Date;
import java.util.Objects;

public class InfantryFightingVehicle extends HangarMilitary {
    private int numberOfGuns;

    public InfantryFightingVehicle() {
        super();
    }

    public InfantryFightingVehicle(String name, java.sql.Date releaseDate, int strength,
                                    long hangarsId, int numberOfGuns) {
        super(name, releaseDate, strength, hangarsId);
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
        InfantryFightingVehicle that = (InfantryFightingVehicle) o;
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
