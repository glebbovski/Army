package com.solvd.army.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
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
            String[] allArmies = new String[MainArmyService.getCountOfArmies()];
            for(int i = 0; i < allArmies.length; i++) {
                allArmies[i] = MainArmyService.getListOfArmies().get(i);
            }

            logger.info("Which army do you want to choose?");
            dashForAnswer(allArmies);
            choice = scanner.nextLine();

            intChoice = -1; // choice for user army
            while(intChoice == -1) { // get army by user
                if (MainArmyService.getListOfArmies().contains((String) choice)) {
                    intChoice = MainArmyService.getListOfArmies().indexOf(choice);
                } else {
                    logger.info("Please, make your choice more correctly.");
                    choice = scanner.nextLine();
                }
            }

            String[] remainingArmies = new String[allArmies.length - 1]; // remaining armies
            int j = 0;
            for(int i = 0; i < allArmies.length; i++) {
                if (i != intChoice) {
                    remainingArmies[j++] = allArmies[i];
                }
            }

            logger.info("Which army do you want to fight?");
            dashForAnswer(remainingArmies);
            choice = scanner.nextLine();

            int secondChoice;
            secondChoice = -1;
            while(secondChoice == -1) { // get army to fight
                for(int i = 0; i < remainingArmies.length; i++) {
                    if (choice.equals(remainingArmies[i])) {
                        secondChoice = i;
                        break;
                    }
                }
                if (secondChoice != -1) {
                    break;
                } else {
                    logger.info("Please, make your choice more correctly.");
                    choice = scanner.nextLine();
                }
            }

            // TODO: here
            // allArmies AND remainingArmies

            MainArmyService.AllAboutArmy allAboutArmy = new MainArmyService.AllAboutArmy(allArmies[intChoice]);
            logger.info(allAboutArmy);




        } else { // new army

        }


        scanner.close();
    }
}
