package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.util.Objects;

public class Commander extends Person {
    private String rank;
    private final int strength = 3;
    private int barracksId;

    public Commander() {
        super();
    }

    public Commander(int id, String name, String surname, String rank, int barracksId) {
        super(id, name, surname);
        this.rank = rank;
        barracksId = barracksId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getStrength() {
        return strength;
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
        Commander commander = (Commander) o;
        return barracksId == commander.barracksId && Objects.equals(rank, commander.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank, strength, barracksId);
    }

    @Override
    public String toString() {
        return super.toString() + "Commander{" +
                "rank='" + rank + '\'' +
                ", strength=" + strength +
                ", barracksId=" + barracksId +
                "}}";
    }
}
