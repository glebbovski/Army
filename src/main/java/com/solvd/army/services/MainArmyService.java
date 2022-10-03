package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.MainArmyDAO;
import com.solvd.army.models.MainArmy;
import com.solvd.army.models.barrack.Barrack;
import com.solvd.army.models.barrack.Beginner;
import com.solvd.army.models.barrack.Commander;
import com.solvd.army.models.barrack.Soldier;
import com.solvd.army.models.hangar.*;
import com.solvd.army.models.jettie.Boat;
import com.solvd.army.models.jettie.Jettie;
import com.solvd.army.models.jettie.Submarine;
import com.solvd.army.models.jettie.Warship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.*;

public class MainArmyService {
    public static final java.sql.Date BEGIN_LEFT_DATE = java.sql.Date.valueOf("2015-03-31");
    public static final java.sql.Date BEGIN_RIGHT_DATE = java.sql.Date.valueOf("2016-03-31");
    public static final java.sql.Date END_LEFT_DATE = java.sql.Date.valueOf("2017-03-31");
    public static final java.sql.Date END_RIGHT_DATE = java.sql.Date.valueOf("2018-03-31");

    private static final Logger logger = LogManager.getLogger(MainArmyService.class);
    private static final Scanner scanner = new Scanner(System.in);
    public static final MainArmyDAO mainArmyDAO = new MainArmyDAO();

    public static class AllAboutArmy {

        private long id;
        private final MainArmy mainArmy;
        private final List<Barrack> barracks;
        private final List<Hangar> hangars;
        private final List<Jettie> jetties;
        private final List<Soldier> soldiers = new ArrayList<>();
        private final List<Commander> commanders = new ArrayList<>();
        private final List<Beginner> beginners = new ArrayList<>();
        private final List<Aircraft> aircrafts = new ArrayList<>();
        private final List<Helicopter> helicopters = new ArrayList<>();
        private final List<ArmoredPersonnelCarrier> armoredPersonnelCarriers = new ArrayList<>();
        private final List<Tank> tanks = new ArrayList<>();
        private final List<UAV> uavs = new ArrayList<>();
        private final List<InfantryFightingVehicle> infantryFightingVehicleList = new ArrayList<>();
        private final List<Warship> warships = new ArrayList<>();
        private final List<Submarine> submarines = new ArrayList<>();
        private final List<Boat> boats = new ArrayList<>();

        private long barrackStrength = 0;
        private long hangarStrength = 0;
        private long jettieStrength = 0;

        public AllAboutArmy(String name) throws AttributeNotFoundException {
            id = MainArmyService.mainArmyDAO.getIdByName(name);
            if (id == 0) {
                throw new AttributeNotFoundException();
            }
            mainArmy = MainArmyService.mainArmyDAO.getById(id);

            barracks = BarrackService.barrackDAO.getAllBarracksByArmyId(id);
            hangars = HangarService.hangarDAO.getAllHangarsByArmyId(id);
            jetties = JettieService.jettieDAO.getAllJettiesByArmyId(id);

            BarrackService.addAllStaffForBarracks(barracks, soldiers, commanders, beginners);

            HangarService.addAllStaffForHangars(hangars, aircrafts, helicopters, armoredPersonnelCarriers, tanks,
                    uavs, infantryFightingVehicleList);

            JettieService.addAllStaffForJetties(jetties, warships, submarines, boats);

            barrackStrength += SoldierService.getStrength(soldiers);
            barrackStrength += CommanderService.getStrength(commanders);
            barrackStrength += BeginnerService.getStrength(beginners);

            hangarStrength += AircraftService.getStrength(aircrafts);
            hangarStrength += HelicopterService.getStrength(helicopters);
            hangarStrength += APCService.getStrength(armoredPersonnelCarriers);
            hangarStrength += TankService.getStrength(tanks);
            hangarStrength += UAVService.getStrength(uavs);
            hangarStrength += IFVService.getStrength(infantryFightingVehicleList);

            jettieStrength += WarshipService.getStrength(warships);
            jettieStrength += SubmarineService.getStrength(submarines);
            jettieStrength += BoatService.getStrength(boats);

        }

        public MainArmy getMainArmy() {
            return mainArmy;
        }

        public List<Barrack> getBarracks() {
            return barracks;
        }

        public List<Hangar> getHangars() {
            return hangars;
        }

        public List<Jettie> getJetties() {
            return jetties;
        }

        public List<Soldier> getSoldiers() {
            return soldiers;
        }

        public List<Commander> getCommanders() {
            return commanders;
        }

        public List<Beginner> getBeginners() {
            return beginners;
        }

        public List<Aircraft> getAircrafts() {
            return aircrafts;
        }

        public List<Helicopter> getHelicopters() {
            return helicopters;
        }

        public List<ArmoredPersonnelCarrier> getArmoredPersonnelCarriers() {
            return armoredPersonnelCarriers;
        }

        public List<Tank> getTanks() {
            return tanks;
        }

        public List<UAV> getUavs() {
            return uavs;
        }

        public List<InfantryFightingVehicle> getInfantryFightingVehicleList() {
            return infantryFightingVehicleList;
        }

        public List<Warship> getWarships() {
            return warships;
        }

        public List<Submarine> getSubmarines() {
            return submarines;
        }

        public List<Boat> getBoats() {
            return boats;
        }

        public long getBarrackStrength() {
            return barrackStrength;
        }

        public long getHangarStrength() {
            return hangarStrength;
        }

        public long getJettieStrength() {
            return jettieStrength;
        }

        public void setBarrackStrength(long barrackStrength) {
            this.barrackStrength = barrackStrength;
        }

        public void setHangarStrength(long hangarStrength) {
            this.hangarStrength = hangarStrength;
        }

        public void setJettieStrength(long jettieStrength) {
            this.jettieStrength = jettieStrength;
        }

        @Override
        public String toString() {
            return "AllAboutArmy{" +
                    "mainArmy=" + mainArmy +
                    ", \nbarracks=" + barracks +
                    ", \nhangars=" + hangars +
                    ", \njetties=" + jetties +
                    ", \nsoldiers=" + soldiers +
                    ", \ncommanders=" + commanders +
                    ", \nbeginners=" + beginners +
                    ", \naircrafts=" + aircrafts +
                    ", \nhelicopters=" + helicopters +
                    ", \narmoredPersonnelCarriers=" + armoredPersonnelCarriers +
                    ", \ntanks=" + tanks +
                    ", \nuavs=" + uavs +
                    ", \ninfantryFightingVehicleList=" + infantryFightingVehicleList +
                    ", \nwarships=" + warships +
                    ", \nsubmarines=" + submarines +
                    ", \nboats=" + boats +
                    ", \nbarrackStrength=" + barrackStrength +
                    ", \nhangarStrength=" + hangarStrength +
                    ", \njettieStrength=" + jettieStrength +
                    '}';
        }
    }


    public static int getCountOfArmies() {
        return MainArmyService.mainArmyDAO.getRowsCount();
    }

    public static void addNewArmy() { // returns name of the new army
        List<String> namesList = MainArmyService.getListOfArmiesNames();
        String[] namesArr = namesList.toArray(new String[0]);
        logger.info("We already have armies: ");
        MainService.dashForAnswer(namesArr);
        logger.info("Please, enter the unique name of your army (like German, Finland etc.): ");
        String armyName = new String();
        armyName = scanner.nextLine() + "_Army";

        while(namesList.contains(armyName)) {
            logger.info("Name of your army should be unique! Try again.");
            armyName = scanner.nextLine() + "_Army";
        }
        int rating = -1;
        while(rating < 0) {
            try {
                logger.info("Please, enter rating of your army above zero: ");
                rating = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.info("Please, enter the correct NUMBER!!!");
                rating = -1;
            }
        }

        // New army object start
        MainArmy newArmy = new MainArmy();
        newArmy.setName(armyName);
        newArmy.setRating(rating);
        try {
            MainArmyService.mainArmyDAO.create(newArmy);
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        }
        newArmy.setId(MainArmyService.mainArmyDAO.getIdByName(armyName));
        // New army object end

        // barracks start
        BarrackService.addBarracks(newArmy.getId());
        // barracks end

        // hangars start
        HangarService.addHangars(newArmy.getId());
        //hangars end

        //jetties start
        JettieService.addJettties(newArmy.getId());
        // jetties end
    }


    public static void fight(boolean withoutAdding) throws AttributeNotFoundException {
        String[] allArmies = new String[MainArmyService.getCountOfArmies()];
        for(int i = 0; i < allArmies.length; i++) {
            allArmies[i] = MainArmyService.getListOfArmiesNames().get(i);
        }

        String choice = "";
        int intChoice;

        if (withoutAdding == true) {
            logger.info("Which army do you want to choose?");
            MainService.dashForAnswer(allArmies);
            choice = scanner.nextLine();

            intChoice = -1; // choice for user army
            while(intChoice == -1) { // get army by user
                if (MainArmyService.getListOfArmiesNames().contains((String) choice)) {
                    intChoice = MainArmyService.getListOfArmiesNames().indexOf(choice);
                } else {
                    logger.info("Please, make your choice more correctly.");
                    choice = scanner.nextLine();
                }
            }
        } else {
            intChoice = allArmies.length - 1;
        }

        String[] remainingArmies = new String[allArmies.length - 1]; // remaining armies
        int j = 0;
        for(int i = 0; i < allArmies.length; i++) {
            if (i != intChoice) {
                remainingArmies[j++] = allArmies[i];
            }
        }

        logger.info("Which army do you want to fight?");
        MainService.dashForAnswer(remainingArmies);
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
        MainService.dashForAnswer("YES");
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

        MainService.dashForAnswer("IT IS TIME TO FIGHT");
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
        MainService.dashForAnswer("                ",
                allAboutMainArmy.getMainArmy().getName(), allAboutSecondArmy.getMainArmy().getName());
        MainService.dashForAnswer("barrack strength", allAboutMainArmy.getBarrackStrength() + "",
                allAboutSecondArmy.getBarrackStrength() + "");
        MainService.dashForAnswer("hangar strength ", allAboutMainArmy.getHangarStrength() + "",
                allAboutSecondArmy.getHangarStrength() + "");
        MainService.dashForAnswer("jettie strength ", allAboutMainArmy.getJettieStrength() + "",
                allAboutSecondArmy.getJettieStrength() + "");

        int winner = MainArmyService.whoStronger(barrackWinner, hangarWinner, jettieWinner);

        logger.info(winner == 1 ? "Winner is " + allAboutMainArmy.getMainArmy().getName() + "!" : winner == 2 ?
                "Winner is " + allAboutSecondArmy.getMainArmy().getName() + "!" : "We have a draw!");

    }

    public static List<String> getListOfArmiesNames() {
        return MainArmyService.mainArmyDAO.getArmiesNames();
    }

    public static int whoStronger(int barrackWinner, int hangarWinner, int jettieWinner) {
        if (barrackWinner == 1 && hangarWinner == 1 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 1 && hangarWinner == 1 && jettieWinner == 2) {
            return 1;
        } else if (barrackWinner == 1 && hangarWinner == 1 && jettieWinner == 3) {
            return 1;
        } else if (barrackWinner == 1 && hangarWinner == 2 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 1 && hangarWinner == 2 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 1 && hangarWinner == 2 && jettieWinner == 3) {
            return 2;
        } else if (barrackWinner == 1 && hangarWinner == 3 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 1 && hangarWinner == 3 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 1 && hangarWinner == 3 && jettieWinner == 3) {
            return 1;
        } else if (barrackWinner == 2 && hangarWinner == 1 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 2 && hangarWinner == 1 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 2 && hangarWinner == 1 && jettieWinner == 3) {
            return 1;
        } else if (barrackWinner == 2 && hangarWinner == 2 && jettieWinner == 1) {
            return 2;
        } else if (barrackWinner == 2 && hangarWinner == 2 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 2 && hangarWinner == 2 && jettieWinner == 3) {
            return 2;
        } else if (barrackWinner == 2 && hangarWinner == 3 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 2 && hangarWinner == 3 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 2 && hangarWinner == 3 && jettieWinner == 3) {
            return 2;
        } else if (barrackWinner == 3 && hangarWinner == 1 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 3 && hangarWinner == 1 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 3 && hangarWinner == 1 && jettieWinner == 3) {
            return 1;
        } else if (barrackWinner == 3 && hangarWinner == 2 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 3 && hangarWinner == 2 && jettieWinner == 2) {
            return 2;
        } else if (barrackWinner == 3 && hangarWinner == 2 && jettieWinner == 3) {
            return 2;
        } else if (barrackWinner == 3 && hangarWinner == 3 && jettieWinner == 1) {
            return 1;
        } else if (barrackWinner == 3 && hangarWinner == 3 && jettieWinner == 2) {
            return 2;
        } else {
            return 3;
        }

    }
}
