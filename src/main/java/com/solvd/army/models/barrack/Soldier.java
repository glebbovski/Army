package com.solvd.army.models.barrack;

import com.solvd.army.models.abstractClasses.Person;

import java.util.Objects;

public class Soldier extends Person {
    private String rank;
    private final int strength = 2;
    private int Barracks_id;

    public Soldier() {
        super();
    }

    public Soldier(int id, String name, String surname, String rank, int barracks_id) {
        super(id, name, surname);
        this.rank = rank;
        Barracks_id = barracks_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getBarracks_id() {
        return Barracks_id;
    }

    public void setBarracks_id(int barracks_id) {
        Barracks_id = barracks_id;
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
        return Barracks_id == soldier.Barracks_id && Objects.equals(rank, soldier.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank, strength, Barracks_id);
    }

    @Override
    public String toString() {
        return super.toString() + "Soldier{" +
                "rank='" + rank + '\'' +
                ", strength=" + strength +
                ", Barracks_id=" + Barracks_id +
                "}}";
    }
}
