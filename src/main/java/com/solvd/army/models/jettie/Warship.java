package com.solvd.army.models.jettie;

import com.solvd.army.models.abstractClasses.Ship;

import java.sql.Date;
import java.util.Objects;

public class Warship extends Ship {
    private int numberOfGuns;
    private int numberOfBombs;

    public Warship() {
        super();
    }

    public Warship(int id, String name, Date releaseDate, int strength, int jetties_id,
                   int numberOfGuns, int numberOfBombs) {
        super(id, name, releaseDate, strength, jetties_id);
        this.numberOfGuns = numberOfGuns;
        this.numberOfBombs = numberOfBombs;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
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
        Warship warship = (Warship) o;
        return numberOfGuns == warship.numberOfGuns && numberOfBombs == warship.numberOfBombs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfGuns, numberOfBombs);
    }

    @Override
    public String toString() {
        return super.toString() + "Warship{" +
                "numberOfGuns=" + numberOfGuns +
                ", numberOfBombs=" + numberOfBombs +
                "}}";
    }
}
