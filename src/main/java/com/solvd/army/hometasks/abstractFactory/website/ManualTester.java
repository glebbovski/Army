package com.solvd.army.hometasks.abstractFactory.website;

import com.solvd.army.hometasks.abstractFactory.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println("Manual tester works on website...");
    }
}
