package com.solvd.army.abstractFactory.banking;

import com.solvd.army.abstractFactory.ProjectManager;

public class BankingPM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println("BankingPM manages code...");
    }
}
