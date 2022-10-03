package com.solvd.army.hometasks.abstractFactory;

public interface ProjectTeamFactory{
    Developer getDeveloper();
    Tester getTester();
    ProjectManager getProjectManager();
}
