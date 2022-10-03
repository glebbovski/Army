package com.solvd.army.abstractFactory;

public interface ProjectTeamFactory{
    Developer getDeveloper();
    Tester getTester();
    ProjectManager getProjectManager();
}
