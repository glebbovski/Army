package com.solvd.army.models.hangar;

import com.solvd.army.models.abstractClasses.HangarMilitary;

import java.sql.Date;
import java.util.Objects;

public class UAV extends HangarMilitary {
    private int numberOfBombs;

    public UAV() {
        super();
    }

    public UAV(int id, String name, Date releaseDate, int strength, int hangars_id, int numberOfBombs) {
        super(id, name, releaseDate, strength, hangars_id);
        this.numberOfBombs = numberOfBombs;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UAV uav = (UAV) o;
        return numberOfBombs == uav.numberOfBombs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfBombs);
    }

    @Override
    public String toString() {
        return super.toString() + "UAV{" +
                "numberOfBombs=" + numberOfBombs +
                "}}";
    }
}
