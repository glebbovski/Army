package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.barrack.BarrackDAO;
import com.solvd.army.models.barrack.Barrack;
import com.solvd.army.models.barrack.Beginner;
import com.solvd.army.models.barrack.Commander;
import com.solvd.army.models.barrack.Soldier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BarrackService {
    private static final Logger logger = LogManager.getLogger(BarrackService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final BarrackDAO barrackDAO = new BarrackDAO();

    public static void addAllStaffForBarracks(List<Barrack> barracks, List<Soldier> soldiers,
                                              List<Commander> commanders, List<Beginner> beginners) {
        for(Barrack barrack : barracks) {
            List<Soldier> lstSoldier = SoldierService.soldierDAO.getAllByBarrackId(barrack.getId());
            SoldierService.addAllSoldiers(soldiers, lstSoldier);

            List<Commander> lstCommander = CommanderService.commanderDAO.getAllByBarrackId(barrack.getId());
            CommanderService.addAllCommanders(commanders, lstCommander);

            List<Beginner> lstBeginner = BeginnerService.beginnerDAO.getAllByBarrackId(barrack.getId());
            BeginnerService.addAllBeginners(beginners, lstBeginner);

        }
    }

    public static void addBarracks(long armyId) {
        logger.info("You need to add some barracks.");
        int numberOfBarracks = -1;
        while(numberOfBarracks < 1 || numberOfBarracks > 3) {
            logger.info("Choose the number of barracks -> 1, 2 or 3");
            try {
                numberOfBarracks = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.info("Please, enter correct NUMBER!");
            }
        }

        for(int i = 0; i < numberOfBarracks; i++) {
            Barrack barrack = new Barrack();
            barrack.setNumberOfFloors(MainService.getRandomNumber(1, 5));
            barrack.setNumberOfBeds(MainService.getRandomNumber(1, 10));
            barrack.setArmyId(armyId);
            try {
                BarrackService.barrackDAO.create(barrack);
                barrack.setId(BarrackService.barrackDAO.getObjectId(barrack));
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }

            logger.info("Please, write the number of people for barrack #"+ (i + 1) +" of your army " +
                    "(they will be randomly distributed)");
            int current = -1;
            while(current < 0 || current > 30) {
                logger.info("Please, select a number more then 0 and less or equal to 30");
                try {
                    current = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    logger.info("Please, enter correct NUMBER!");
                }
            }
            int countForSoldiers = MainService.getRandomNumber(1, current);
            current -= countForSoldiers;
            int countForCommanders = 0;
            int countForBeginners = 0;
            if (current > 0) {
                countForCommanders = MainService.getRandomNumber(1, current);
            }
            current -= countForCommanders;
            if (current > 0) {
                countForBeginners = current;
            }
            SoldierService.addSoldiersForBarrack(countForSoldiers, barrack.getId());
            CommanderService.addCommandersForBarrack(countForCommanders, barrack.getId());
            BeginnerService.addBeginnersForBarrack(countForBeginners, barrack.getId());

        }

    }
}
