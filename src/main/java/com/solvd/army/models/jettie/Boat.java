package com.solvd.army.models.jettie;

import com.solvd.army.models.abstractClasses.Ship;

import java.util.Date;
import java.util.Objects;

public class Boat extends Ship {
    private int numberOfGuns;

    public Boat() {
        super();
    }

    public Boat(int id, String name, Date releaseDate, int strength, int jetties_id, int numberOfGuns) {
        super(id, name, releaseDate, strength, jetties_id);
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
        Boat boat = (Boat) o;
        return numberOfGuns == boat.numberOfGuns;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfGuns);
    }

    @Override
    public String toString() {
        return super.toString() + "Boat{" +
                "numberOfGuns=" + numberOfGuns +
                "}}";
    }
}
