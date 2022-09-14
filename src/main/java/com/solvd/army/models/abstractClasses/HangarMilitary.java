package com.solvd.army.models.abstractClasses;

import java.sql.Date;
import java.util.Objects;

public abstract class HangarMilitary {
    private int id;
    private String name;
    private Date releaseDate;
    private int strength;
    private int hangarsId;

    public HangarMilitary() {
    }

    public HangarMilitary(int id, String name, Date releaseDate, int strength, int hangarsId) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.strength = strength;
        hangarsId = hangarsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHangarsId() {
        return hangarsId;
    }

    public void setHangarsId(int hangarsId) {
        hangarsId = hangarsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HangarMilitary that = (HangarMilitary) o;
        return id == that.id && strength == that.strength
                && hangarsId == that.hangarsId && Objects.equals(name, that.name)
                && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, strength, hangarsId);
    }

    @Override
    public String toString() {
        return "HangarMilitary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", strength=" + strength +
                ", hangarsId=" + hangarsId +
                ", ";
    }
}
