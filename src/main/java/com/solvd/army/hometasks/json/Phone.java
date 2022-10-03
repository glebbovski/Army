package com.solvd.army.hometasks.json;

public class Phone {
    private String mark;
    private int year;
    private String ownerName;
    private String colour;

    public Phone() {
    }

    public Phone(String mark, int year, String ownerName, String colour) {
        this.mark = mark;
        this.year = year;
        this.ownerName = ownerName;
        this.colour = colour;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mark='" + mark + '\'' +
                ", year=" + year +
                ", ownerName='" + ownerName + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
