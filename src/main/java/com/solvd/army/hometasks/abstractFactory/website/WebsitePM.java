package com.solvd.army.hometasks.abstractFactory.website;

import com.solvd.army.hometasks.abstractFactory.ProjectManager;

public class WebsitePM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println("WebsitePM manages website...");
    }
}
