package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.sql.Date;
import java.util.Objects;

public class Beginner extends Person {
    private Date beginDate;
    private Date endDate;
    private int Barracks_id;

    public Beginner() {
        super();
    }

    public Beginner(int id, String name, String surname, Date beginDate, Date endDate, int barracks_id) {
        super(id, name, surname);
        this.beginDate = beginDate;
        this.endDate = endDate;
        Barracks_id = barracks_id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getBarracks_id() {
        return Barracks_id;
    }

    public void setBarracks_id(int barracks_id) {
        Barracks_id = barracks_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beginner beginner = (Beginner) o;
        return Barracks_id == beginner.Barracks_id && Objects.equals(beginDate, beginner.beginDate)
                && Objects.equals(endDate, beginner.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), beginDate, endDate, Barracks_id);
    }

    @Override
    public String toString() {
        return super.toString() + "Beginner{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", Barracks_id=" + Barracks_id +
                "}}";
    }
}
