package com.solvd.army.models.hangar;

import com.solvd.army.models.abstractClasses.HangarMilitary;

import java.util.Objects;

public class Tank extends HangarMilitary {
    private int numberOfGuns;
    private int centimetersOfArmor;

    public Tank() {
        super();
    }

    public Tank(String name, java.sql.Date releaseDate, int strength, long hangarsId,
                int numberOfGuns, int centimetersOfArmor) {
        super(name, releaseDate, strength, hangarsId);
        this.numberOfGuns = numberOfGuns;
        this.centimetersOfArmor = centimetersOfArmor;
    }

    public int getNumberOfGuns() {
        return numberOfGuns;
    }

    public void setNumberOfGuns(int numberOfGuns) {
        this.numberOfGuns = numberOfGuns;
    }

    public int getCentimetersOfArmor() {
        return centimetersOfArmor;
    }

    public void setCentimetersOfArmor(int centimetersOfArmor) {
        this.centimetersOfArmor = centimetersOfArmor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tank tank = (Tank) o;
        return numberOfGuns == tank.numberOfGuns && centimetersOfArmor == tank.centimetersOfArmor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfGuns, centimetersOfArmor);
    }

    @Override
    public String toString() {
        return super.toString() + "Tank{" +
                "numberOfGuns=" + numberOfGuns +
                ", centimetersOfArmor=" + centimetersOfArmor +
                "}}";
    }
}
