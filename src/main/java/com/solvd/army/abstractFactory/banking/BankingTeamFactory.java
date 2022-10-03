package com.solvd.army.abstractFactory.banking;

import com.solvd.army.abstractFactory.Developer;
import com.solvd.army.abstractFactory.ProjectManager;
import com.solvd.army.abstractFactory.ProjectTeamFactory;
import com.solvd.army.abstractFactory.Tester;

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
