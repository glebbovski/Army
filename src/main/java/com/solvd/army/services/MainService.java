package com.solvd.army.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainService {
    private static final Logger logger = LogManager.getLogger(MainService.class);
    private static final Scanner scanner = new Scanner(System.in);

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
            if (i != topBotStr.length() - 3 && topBotStr.charAt(i + 1) == '|') {
                strMinus += "+";
            } else {
                strMinus += "-";
            }
        }
        strMinus += "+";
        logger.info(strMinus);
        logger.info(topBotStr);
        logger.info(centralStr);
        logger.info(topBotStr);
        logger.info(strMinus);

    }

    public static String getRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static java.sql.Date getRandomDate(java.sql.Date startInclusive, java.sql.Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new java.sql.Date(randomMillisSinceEpoch);
    }

    public static void mainWork() throws AttributeNotFoundException {
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
            MainArmyService.fight(true);
        } else { // new army
            MainArmyService.addNewArmy();
            MainArmyService.fight(false);
        }
        scanner.close();
    }
}
