package com.solvd.army.abstractFactory.website;

import com.solvd.army.abstractFactory.Developer;
import com.solvd.army.abstractFactory.ProjectManager;
import com.solvd.army.abstractFactory.ProjectTeamFactory;
import com.solvd.army.abstractFactory.Tester;

public class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManager getProjectManager() {
        return new WebsitePM();
    }
}
