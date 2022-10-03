package com.solvd.army.hometasks.abstractFactory.banking;

import com.solvd.army.hometasks.abstractFactory.Developer;

public class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes Java code...");
    }
}
