package com.solvd.army.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Scanner;

public class MainService {
    private static final Logger logger = LogManager.getLogger(MainService.class);
    private static Scanner scanner = new Scanner(System.in);

    public static void dashForAnswer(String... answers) {
        String strMinus = "+";
        String topBotStr = "|";
        String centralStr = "|";
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < answers[i].length() + 2; j++) {
                if (j == 0) {
                    centralStr += " ";
                    topBotStr += " ";
                } else if (j > 0 && j < answers[i].length() + 1) {
                    centralStr += answers[i].charAt(j - 1);
                    topBotStr += " ";
                } else {
                    centralStr += " |";
                    topBotStr += " |";
                }
            }
        }
        for (int i = 0; i < topBotStr.length() - 2; i++) {
            strMinus += "-";
        }
        strMinus += "+";
        logger.info(strMinus);
        logger.info(topBotStr);
        logger.info(centralStr);
        logger.info(topBotStr);
        logger.info(strMinus);

    }

    public static void mainWork() {
        logger.info("Hello! We are glad to see you in our army-game. We have " + MainArmyService.getCountOfArmies() +
                " basic armies.");
        logger.info("Do you want to play with basic armies or want to add another one?");
        dashForAnswer("basic", "new");

        String choice = scanner.nextLine();
        int intChoice = 0;

        while(intChoice == 0) {
            switch (choice) {
                case "basic":
                    intChoice = 1;
                    break;
                case "new":
                    intChoice = 2;
                    break;
                default:
                    logger.info("Please, make your choice more correctly.");
                    choice = scanner.nextLine();
            }
        }

        if (intChoice == 1) { // basic army
            String[] tmp = new String[MainArmyService.getCountOfArmies()];
            for(int i = 0; i < tmp.length; i++) {
                tmp[i] = MainArmyService.getListOfArmies().get(i);
            }
            dashForAnswer(tmp);

            // TODO: here


        } else { // new army

        }


        scanner.close();
    }
}
