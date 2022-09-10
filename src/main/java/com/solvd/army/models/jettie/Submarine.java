package com.solvd.army.models.jettie;

import com.solvd.army.models.abstractClasses.Ship;

import java.util.Date;
import java.util.Objects;

public class Submarine extends Ship {
    private int numberOfBombs;
    private int numberOfEchoSounders;

    public Submarine() {
        super();
    }

    public Submarine(int id, String name, Date releaseDate, int strength, int jetties_id,
                     int numberOfBombs, int numberOfEchoSounders) {
        super(id, name, releaseDate, strength, jetties_id);
        this.numberOfBombs = numberOfBombs;
        this.numberOfEchoSounders = numberOfEchoSounders;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public int getNumberOfEchoSounders() {
        return numberOfEchoSounders;
    }

    public void setNumberOfEchoSounders(int numberOfEchoSounders) {
        this.numberOfEchoSounders = numberOfEchoSounders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Submarine submarine = (Submarine) o;
        return numberOfBombs == submarine.numberOfBombs && numberOfEchoSounders == submarine.numberOfEchoSounders;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfBombs, numberOfEchoSounders);
    }

    @Override
    public String toString() {
        return super.toString() + "Submarine{" +
                "numberOfBombs=" + numberOfBombs +
                ", numberOfEchoSounders=" + numberOfEchoSounders +
                "}}";
    }


}
