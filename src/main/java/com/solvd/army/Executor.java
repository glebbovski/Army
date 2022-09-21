package com.solvd.army;

import com.solvd.army.services.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;

public class Executor {

    private static final Logger logger = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
        MainService.mainWork();
    }
}
