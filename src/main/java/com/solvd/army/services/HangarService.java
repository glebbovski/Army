package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.HangarDAO;
import com.solvd.army.models.hangar.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Scanner;

public class HangarService {
    private static final Logger logger = LogManager.getLogger(HangarService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final HangarDAO hangarDAO = new HangarDAO();

    public static void addAllStaffForHangars(List<Hangar> hangars, List<Aircraft> aircrafts,
                                             List<Helicopter> helicopters,
                                             List<ArmoredPersonnelCarrier> armoredPersonnelCarriers,
                                             List<Tank> tanks, List<UAV> uavs,
                                             List<InfantryFightingVehicle> infantryFightingVehicleList) {
        for(Hangar hangar : hangars) {
            List<Aircraft> tmpAircraft = AircraftService.aircraftDAO.getAllByHangarId(hangar.getId());
            AircraftService.addAllAircrafts(aircrafts, tmpAircraft);

            List<Helicopter> tmpHelicopter = HelicopterService.helicopterDAO.getAllByHangarId(hangar.getId());
            HelicopterService.addAllHelicopters(helicopters, tmpHelicopter);

            List<ArmoredPersonnelCarrier> tmpCarries = APCService.carrierDAO.getAllByHangarId(hangar.getId());
            APCService.addAllAPCs(armoredPersonnelCarriers, tmpCarries);

            List<Tank> tmpTanks = TankService.tankDAO.getAllByHangarId(hangar.getId());
            TankService.addAllTanks(tanks, tmpTanks);

            List<UAV> tmpUavs = UAVService.uavDAO.getAllByHangarId(hangar.getId());
            UAVService.addAllUAVs(uavs, tmpUavs);

            List<InfantryFightingVehicle> tmpVehicles = IFVService.vehicleDAO.getAllByHangarId(hangar.getId());
            IFVService.addAllIFVs(infantryFightingVehicleList, tmpVehicles);
        }
    }

    public static void addHangars(long armyId) {
        logger.info("You need to add some hangars.");
        int numberOfHangars = -1;
        while(numberOfHangars < 1 || numberOfHangars > 3) {
            logger.info("Choose the number of hangars -> 1, 2 or 3");
            try {
                numberOfHangars = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.info("Please, enter correct NUMBER!");
            }
        }

        for(int i = 0; i < numberOfHangars; i++) {
            Hangar hangar = new Hangar();
            hangar.setArmyId(armyId);


            logger.info("Please, write the number of heavy equipment for hangar #"+ (i + 1) +" of your army " +
                    "(they will be randomly distributed)");
            int current = -1;
            while(current < 0 || current > 20) {
                logger.info("Please, select a number more then 0 and less or equal to 20");
                try {
                    current = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    logger.info("Please, enter correct NUMBER!");
                }
            }

            hangar.setNumberOfMilitaryCraft(current);
            try {
                HangarService.hangarDAO.create(hangar);
                hangar.setId(HangarService.hangarDAO.getObjectId(hangar));
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }

            int countForAircrafts = MainService.getRandomNumber(1, current);
            int countForHelicopters = 0;
            int countForAPCs = 0;
            int countForTanks = 0;
            int countForUAVs = 0;
            int countForIFVs = 0;
            current -= countForAircrafts;
            if (current > 0) {
                countForHelicopters = MainService.getRandomNumber(1, current);
            }
            current -= countForHelicopters;
            if (current > 0) {
                countForAPCs = MainService.getRandomNumber(1, current);
            }
            current -= countForAPCs;
            if (current > 0) {
                countForTanks = MainService.getRandomNumber(1, current);
            }
            current -= countForTanks;
            if (current > 0) {
                countForUAVs = MainService.getRandomNumber(1, current);
            }
            current -= countForUAVs;
            if (current > 0) {
                countForIFVs = current;
            }
            AircraftService.addAircraftsForHangar(countForAircrafts, hangar.getId());
            HelicopterService.addHelicoptersForHangar(countForHelicopters, hangar.getId());
            APCService.addAPCsForHangar(countForAPCs, hangar.getId());
            TankService.addTanksForHangar(countForTanks, hangar.getId());
            UAVService.addUAVsForHangar(countForUAVs, hangar.getId());
            IFVService.addIFVsForHangar(countForIFVs, hangar.getId());

        }
    }
}
