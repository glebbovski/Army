package com.solvd.army.hometasks.abstractFactory.website;

import com.solvd.army.hometasks.abstractFactory.Developer;

public class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Php developer writes php code...");
    }
}
