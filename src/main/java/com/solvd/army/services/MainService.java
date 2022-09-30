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

            MainArmyService.AllAboutArmy allAboutMainArmy = new MainArmyService.AllAboutArmy(allArmies[intChoice]);
            logger.info("Trying to get information about your choice...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Got it!");
            logger.info(allAboutMainArmy);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("-------------------------------------------------");
            logger.info("Trying to get information about second army...");
            MainArmyService.AllAboutArmy allAboutSecondArmy =
                    new MainArmyService.AllAboutArmy(remainingArmies[secondChoice]);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Got it!");
            logger.info(allAboutSecondArmy);
            logger.info("-------------------------------------------------");
            logger.info("Are you ready to fight??????");
            dashForAnswer("YES");
            intChoice = 0;
            choice = scanner.nextLine();
            while(intChoice == 0) {
                switch (choice) {
                    case "YES":
                        intChoice = 1;
                        break;
                    default:
                        logger.info("Please, make your choice more correctly.");
                        choice = scanner.nextLine();
                }
            }

            dashForAnswer("IT IS TIME TO FIGHT");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Very fierce battles begin on land...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("The fights are still going on...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Air forces and heavy equipment enter the battle...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Naval battles also begin...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("The fight is almost over...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("We have results...");


            int barrackWinner = -1; // 3 - tie, 1 - main army won, 2 - second army won
            int hangarWinner = -1;  // 3 - tie, 1 - main army won, 2 - second army won
            int jettieWinner = -1;  // 3 - tie, 1 - main army won, 2 - second army won
            // barrack strength
            if (allAboutMainArmy.getBarrackStrength() > allAboutSecondArmy.getBarrackStrength()) {
                allAboutMainArmy.setBarrackStrength(allAboutMainArmy.getBarrackStrength() -
                        allAboutSecondArmy.getBarrackStrength());
                allAboutSecondArmy.setBarrackStrength(0);

                barrackWinner = 1;

            } else if (allAboutMainArmy.getBarrackStrength() < allAboutSecondArmy.getBarrackStrength()) {
                allAboutSecondArmy.setBarrackStrength(allAboutSecondArmy.getBarrackStrength() -
                        allAboutMainArmy.getBarrackStrength());
                allAboutMainArmy.setBarrackStrength(0);

                barrackWinner = 2;
            } else {
                allAboutMainArmy.setBarrackStrength(0);
                allAboutSecondArmy.setBarrackStrength(0);

                barrackWinner = 3;
            }

            // hangar strength

            if (allAboutMainArmy.getHangarStrength() > allAboutSecondArmy.getHangarStrength()) {
                allAboutMainArmy.setHangarStrength(allAboutMainArmy.getHangarStrength() -
                        allAboutSecondArmy.getHangarStrength());
                allAboutSecondArmy.setHangarStrength(0);

                hangarWinner = 1;
            } else if (allAboutMainArmy.getHangarStrength() < allAboutSecondArmy.getHangarStrength()) {
                allAboutSecondArmy.setHangarStrength(allAboutSecondArmy.getHangarStrength() -
                        allAboutMainArmy.getHangarStrength());
                allAboutMainArmy.setHangarStrength(0);

                hangarWinner = 2;
            } else {
                allAboutMainArmy.setHangarStrength(0);
                allAboutSecondArmy.setHangarStrength(0);

                hangarWinner = 3;
            }

            // jettie strength

            if (allAboutMainArmy.getJettieStrength() > allAboutSecondArmy.getJettieStrength()) {
                allAboutMainArmy.setJettieStrength(allAboutMainArmy.getJettieStrength() -
                        allAboutSecondArmy.getJettieStrength());
                allAboutSecondArmy.setJettieStrength(0);

                jettieWinner = 1;
            } else if (allAboutMainArmy.getJettieStrength() < allAboutSecondArmy.getJettieStrength()) {
                allAboutSecondArmy.setJettieStrength(allAboutSecondArmy.getJettieStrength() -
                        allAboutMainArmy.getJettieStrength());
                allAboutMainArmy.setJettieStrength(0);

                jettieWinner = 2;
            } else {
                allAboutMainArmy.setJettieStrength(0);
                allAboutSecondArmy.setJettieStrength(0);

                jettieWinner = 3;
            }
            dashForAnswer("                ",
                    allAboutMainArmy.getMainArmy().getName(), allAboutSecondArmy.getMainArmy().getName());
            dashForAnswer("barrack strength", allAboutMainArmy.getBarrackStrength() + "",
                    allAboutSecondArmy.getBarrackStrength() + "");
            dashForAnswer("hangar strength ", allAboutMainArmy.getHangarStrength() + "",
                    allAboutSecondArmy.getHangarStrength() + "");
            dashForAnswer("jettie strength ", allAboutMainArmy.getJettieStrength() + "",
                    allAboutSecondArmy.getJettieStrength() + "");

            int winner = MainArmyService.whoStronger(barrackWinner, hangarWinner, jettieWinner);

            logger.info(winner == 1 ? "Winner is " + allAboutMainArmy.getMainArmy().getName() : winner == 2 ?
                    "Winner is " + allAboutSecondArmy.getMainArmy().getName() : "We have a draw!");

        } else { // new army

        }


        scanner.close();
    }
}
