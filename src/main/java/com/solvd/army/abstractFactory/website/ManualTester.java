package com.solvd.army.abstractFactory.website;

import com.solvd.army.abstractFactory.Tester;

public class ManualTester implements Tester {
    @Override
    public void testCode() {
        System.out.println("Manual tester works on website...");
    }
}
