package com.solvd.army.hometasks.abstractFactory.website;

import com.solvd.army.hometasks.abstractFactory.Developer;
import com.solvd.army.hometasks.abstractFactory.ProjectManager;
import com.solvd.army.hometasks.abstractFactory.ProjectTeamFactory;
import com.solvd.army.hometasks.abstractFactory.Tester;

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
