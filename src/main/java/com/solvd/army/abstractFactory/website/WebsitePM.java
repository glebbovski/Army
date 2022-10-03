package com.solvd.army.abstractFactory.website;

import com.solvd.army.abstractFactory.ProjectManager;

public class WebsitePM implements ProjectManager {
    @Override
    public void manageProject() {
        System.out.println("WebsitePM manages website...");
    }
}
