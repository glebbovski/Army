package com.solvd.army.hometasks.decorator;

public class JavaTeamLead extends DeveloperDecorator{
    public JavaTeamLead(Developer developer) {
        super(developer);
    }

    @Override
    public String makeJob() {
        return super.makeJob() + sendWeekReport();
    }

    public String sendWeekReport() {
        return "Send week report to Customer. ";
    }
}
