package com.solvd.army.models.abstractClasses;

import java.util.Objects;

public abstract class HangarMilitary {
    private long id = -1;
    private String name;
    private java.sql.Date releaseDate;
    private int strength;
    private long hangarsId;

    public HangarMilitary() {
    }

    public HangarMilitary(String name, java.sql.Date releaseDate, int strength, long hangarsId) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.strength = strength;
        this.hangarsId = hangarsId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public long getHangarsId() {
        return hangarsId;
    }

    public void setHangarsId(long hangarsId) {
        this.hangarsId = hangarsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HangarMilitary that = (HangarMilitary) o;
        return strength == that.strength
                && hangarsId == that.hangarsId && Objects.equals(name, that.name)
                && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, releaseDate, strength, hangarsId);
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
