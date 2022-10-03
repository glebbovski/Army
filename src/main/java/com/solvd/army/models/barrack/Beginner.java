package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.util.Objects;

public class Beginner extends Person {
    private java.sql.Date beginDate;
    private java.sql.Date endDate;
    private final int strength = 1;
    private long barracksId;

    public Beginner() {
        super();
    }

    public Beginner(String name, String surname, java.sql.Date beginDate, java.sql.Date endDate, long barracksId) {
        super(name, surname);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.barracksId = barracksId;
    }

    public java.sql.Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(java.sql.Date beginDate) {
        this.beginDate = beginDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public long getBarracksId() {
        return barracksId;
    }

    public void setBarracksId(long barracksId) {
        this.barracksId = barracksId;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beginner beginner = (Beginner) o;
        return barracksId == beginner.barracksId && Objects.equals(beginDate, beginner.beginDate) && Objects.equals(endDate, beginner.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), beginDate, endDate, strength, barracksId);
    }

    @Override
    public String toString() {
        return super.toString() + "Beginner{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", strength=" + strength +
                ", barracksId=" + barracksId +
                "}}";
    }
}
