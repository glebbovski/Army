package com.solvd.army.models.hangar;

import com.solvd.army.models.abstractClasses.HangarMilitary;

import java.sql.Date;
import java.util.Objects;

public class Aircraft extends HangarMilitary {
    private int numberOfFlights;

    public Aircraft() {
        super();
    }

    public Aircraft(String name, java.sql.Date releaseDate, int strength, long hangarsId, int numberOfFlights) {
        super(name, releaseDate, strength, hangarsId);
        this.numberOfFlights = numberOfFlights;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(int numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aircraft aircraft = (Aircraft) o;
        return numberOfFlights == aircraft.numberOfFlights;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfFlights);
    }

    @Override
    public String toString() {
        return super.toString() + "Aircraft{" +
                "numberOfFlights=" + numberOfFlights +
                "}}";
    }
}
