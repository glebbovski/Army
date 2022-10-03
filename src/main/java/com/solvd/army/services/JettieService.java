package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.jettie.JettieDAO;
import com.solvd.army.models.jettie.Boat;
import com.solvd.army.models.jettie.Jettie;
import com.solvd.army.models.jettie.Submarine;
import com.solvd.army.models.jettie.Warship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Scanner;

public class JettieService {
    private static final Logger logger = LogManager.getLogger(JettieService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final JettieDAO jettieDAO = new JettieDAO();

    public static void addAllStaffForJetties(List<Jettie> jetties, List<Warship> warships,
                                             List<Submarine> submarines, List<Boat> boats) {
        for(Jettie jettie : jetties) {
            List<Warship> tmpWarship = WarshipService.warshipDAO.getAllByJettieId(jettie.getId());
            WarshipService.addAllWarships(warships, tmpWarship);

            List<Submarine> tmpSubmarine = SubmarineService.submarineDAO.getAllByJettieId(jettie.getId());
            SubmarineService.addAllSubmarines(submarines, tmpSubmarine);

            List<Boat> tmpBoat = BoatService.boatDAO.getAllByJettieId(jettie.getId());
            BoatService.addAllBoats(boats, tmpBoat);
        }

    }

    public static void addJettties(long armyId) {
        logger.info("You need to make some jetties.");
        int numberOfJetties = -1;
        while(numberOfJetties < 1 || numberOfJetties > 3) {
            logger.info("Choose the number of jetties -> 1, 2 or 3");
            try {
                numberOfJetties = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.info("Please, enter correct NUMBER!");
            }
        }

        for(int i = 0; i < numberOfJetties; i++) {
            Jettie jettie = new Jettie();
            jettie.setArmyId(armyId);

            logger.info("Please, write the number of ships for jettie #"+ (i + 1) +" of your army " +
                    "(they will be randomly distributed)");
            int current = -1;
            while(current < 0 || current > 10) {
                logger.info("Please, select a number more then 0 and less or equal to 10");
                try {
                    current = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    logger.info("Please, enter correct NUMBER!");
                }
            }

            jettie.setNumberOfShips(current);
            try {
                JettieService.jettieDAO.create(jettie);
                jettie.setId(JettieService.jettieDAO.getObjectId(jettie));
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }

            int countForWarships = MainService.getRandomNumber(1, current);
            current -= countForWarships;
            int countForSubmarines = 0;
            int countForBoats = 0;
            if (current > 0) {
                countForSubmarines = MainService.getRandomNumber(1, current);
            }
            current -= countForSubmarines;
            if (current > 0) {
                countForBoats = MainService.getRandomNumber(1, current);
            }
            WarshipService.addWarshipsForJettie(countForWarships, jettie.getId());
            SubmarineService.addSubmarinesForJettie(countForSubmarines, jettie.getId());
            BoatService.addBoatsForJettie(countForBoats, jettie.getId());
        }
    }
}
