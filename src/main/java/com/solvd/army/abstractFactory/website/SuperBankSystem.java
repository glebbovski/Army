package com.solvd.army.abstractFactory.website;

import com.solvd.army.abstractFactory.Developer;
import com.solvd.army.abstractFactory.ProjectManager;
import com.solvd.army.abstractFactory.ProjectTeamFactory;
import com.solvd.army.abstractFactory.Tester;
import com.solvd.army.abstractFactory.banking.BankingTeamFactory;

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
