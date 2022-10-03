package com.solvd.army.hometasks.abstractFactory.banking;

import com.solvd.army.hometasks.abstractFactory.Developer;
import com.solvd.army.hometasks.abstractFactory.ProjectManager;
import com.solvd.army.hometasks.abstractFactory.ProjectTeamFactory;
import com.solvd.army.hometasks.abstractFactory.Tester;

public class BankingTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new BankingPM();
    }
}
