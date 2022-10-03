package com.solvd.army.hometasks.abstractFactory.website;

import com.solvd.army.hometasks.abstractFactory.Developer;
import com.solvd.army.hometasks.abstractFactory.ProjectManager;
import com.solvd.army.hometasks.abstractFactory.ProjectTeamFactory;
import com.solvd.army.hometasks.abstractFactory.Tester;
import com.solvd.army.hometasks.abstractFactory.banking.BankingTeamFactory;

public class SuperBankSystem {
    public static void main(String[] args) {
        ProjectTeamFactory factory = new BankingTeamFactory();
        Developer developer = factory.getDeveloper();
        Tester tester = factory.getTester();
        ProjectManager projectManager = factory.getProjectManager();
        System.out.println("Creating bank system...");
        developer.writeCode();
        tester.testCode();
        projectManager.manageProject();
    }
}
