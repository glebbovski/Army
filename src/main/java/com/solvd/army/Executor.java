package com.solvd.army;

import com.solvd.army.services.MainService;

import javax.management.AttributeNotFoundException;

public class Executor {
    public static void main(String[] args) throws AttributeNotFoundException {
        MainService.mainWork();
    }
}
