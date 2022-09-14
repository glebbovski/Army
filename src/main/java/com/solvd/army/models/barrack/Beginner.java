package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.sql.Date;
import java.util.Objects;

public class Beginner extends Person {
    private Date beginDate;
    private Date endDate;
    private int barracksId;

    public Beginner() {
        super();
    }

    public Beginner(int id, String name, String surname, Date beginDate, Date endDate, int barracksId) {
        super(id, name, surname);
        this.beginDate = beginDate;
        this.endDate = endDate;
        barracksId = barracksId;
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

    public int getBarracksId() {
        return barracksId;
    }

    public void setBarracksId(int barracksId) {
        barracksId = barracksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beginner beginner = (Beginner) o;
        return barracksId == beginner.barracksId && Objects.equals(beginDate, beginner.beginDate)
                && Objects.equals(endDate, beginner.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), beginDate, endDate, barracksId);
    }

    @Override
    public String toString() {
        return super.toString() + "Beginner{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", barracksId=" + barracksId +
                "}}";
    }
}
