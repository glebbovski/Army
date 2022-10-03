package com.solvd.army.hometasks.abstractFactory.banking;

import com.solvd.army.hometasks.abstractFactory.ProjectManager;

public class BankingPM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println("BankingPM manages code...");
    }
}
