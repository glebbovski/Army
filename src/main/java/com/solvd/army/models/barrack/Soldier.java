package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.util.Objects;

public class Soldier extends Person {
    private String rank;
    private final int strength = 2;
    private long barracksId;

    public Soldier() {
        super();
    }

    public Soldier(String name, String surname, String rank, long barracksId) {
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
        Soldier soldier = (Soldier) o;
        return barracksId == soldier.barracksId && Objects.equals(rank, soldier.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank, strength, barracksId);
    }

    @Override
    public String toString() {
        return super.toString() + "Soldier{" +
                "rank='" + rank + '\'' +
                ", strength=" + strength +
                ", barracksId=" + barracksId +
                "}}";
    }
}
