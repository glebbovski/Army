package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.util.Objects;

public class Commander extends Person {
    private String rank;
    private final int strength = 3;
    private long barracksId;

    public Commander() {
        super();
    }

    public Commander(String name, String surname, String rank, long barracksId) {
        super(name, surname);
        this.rank = rank;
        this.barracksId = barracksId;
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

    public long getBarracksId() {
        return barracksId;
    }

    public void setBarracksId(long barracksId) {
        this.barracksId = barracksId;
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
