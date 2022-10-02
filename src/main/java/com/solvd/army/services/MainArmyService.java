package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.MainArmyDAO;
import com.solvd.army.dao.jdbc.mysql.barrack.BarrackDAO;
import com.solvd.army.dao.jdbc.mysql.barrack.BeginnerDAO;
import com.solvd.army.dao.jdbc.mysql.barrack.CommanderDAO;
import com.solvd.army.dao.jdbc.mysql.barrack.SoldierDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.*;
import com.solvd.army.dao.jdbc.mysql.jettie.BoatDAO;
import com.solvd.army.dao.jdbc.mysql.jettie.JettieDAO;
import com.solvd.army.dao.jdbc.mysql.jettie.SubmarineDAO;
import com.solvd.army.dao.jdbc.mysql.jettie.WarshipDAO;
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
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainArmyService {
    private static final java.sql.Date BEGIN_LEFT_DATE = java.sql.Date.valueOf("2015-03-31");
    private static final java.sql.Date BEGIN_RIGHT_DATE = java.sql.Date.valueOf("2016-03-31");
    private static final java.sql.Date END_LEFT_DATE = java.sql.Date.valueOf("2017-03-31");
    private static final java.sql.Date END_RIGHT_DATE = java.sql.Date.valueOf("2018-03-31");

    private static final Logger logger = LogManager.getLogger(MainArmyService.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static MainArmyDAO mainArmyDAO = new MainArmyDAO();
    private static BarrackDAO barrackDAO = new BarrackDAO();
    private static HangarDAO hangarDAO = new HangarDAO();
    private static JettieDAO jettieDAO = new JettieDAO();
    private static SoldierDAO soldierDAO = new SoldierDAO();
    private static BeginnerDAO beginnerDAO = new BeginnerDAO();
    private static CommanderDAO commanderDAO = new CommanderDAO();
    private static AircraftDAO aircraftDAO = new AircraftDAO();
    private static HelicopterDAO helicopterDAO = new HelicopterDAO();
    private static ArmoredPersonnelCarrierDAO carrierDAO = new ArmoredPersonnelCarrierDAO();
    private static TankDAO tankDAO = new TankDAO();
    private static UAVDAO uavDAO = new UAVDAO();
    private static InfantryFightingVehicleDAO vehicleDAO = new InfantryFightingVehicleDAO();
    private static WarshipDAO warshipDAO = new WarshipDAO();
    private static SubmarineDAO submarineDAO = new SubmarineDAO();
    private static BoatDAO boatDAO = new BoatDAO();

    public static class AllAboutArmy {

        private long id;
        private MainArmy mainArmy;
        private List<Barrack> barracks;
        private List<Hangar> hangars;
        private List<Jettie> jetties;
        private List<Soldier> soldiers = new ArrayList<>();
        private List<Commander> commanders = new ArrayList<>();
        private List<Beginner> beginners = new ArrayList<>();
        private List<Aircraft> aircrafts = new ArrayList<>();
        private List<Helicopter> helicopters = new ArrayList<>();
        private List<ArmoredPersonnelCarrier> armoredPersonnelCarriers = new ArrayList<>();
        private List<Tank> tanks = new ArrayList<>();
        private List<UAV> uavs = new ArrayList<>();
        private List<InfantryFightingVehicle> infantryFightingVehicleList = new ArrayList<>();
        private List<Warship> warships = new ArrayList<>();
        private List<Submarine> submarines = new ArrayList<>();
        private List<Boat> boats = new ArrayList<>();

        private long barrackStrength = 0;
        private long hangarStrength = 0;
        private long jettieStrength = 0;

        public AllAboutArmy(String name) throws AttributeNotFoundException {
            id = mainArmyDAO.getIdByName(name);
            if (id == 0) {
                throw new AttributeNotFoundException();
            }
            mainArmy = mainArmyDAO.getById(id);

            barracks = barrackDAO.getAllBarracksByArmyId(id);
            hangars = hangarDAO.getAllHangarsByArmyId(id);
            jetties = jettieDAO.getAllJettiesByArmyId(id);

            for(Barrack barrack : barracks) {
                List<Soldier> lstSoldier = soldierDAO.getAllByBarrackId(barrack.getId());
                if (lstSoldier != null) {
                    soldiers.addAll(lstSoldier);
                }
                List<Commander> lstCommander = commanderDAO.getAllByBarrackId(barrack.getId());
                if (lstCommander != null) {
                    commanders.addAll(lstCommander);
                }
                List<Beginner> lstBeginner = beginnerDAO.getAllByBarrackId(barrack.getId());
                if (lstBeginner != null) {
                    beginners.addAll(lstBeginner);
                }
            }
            for(Hangar hangar : hangars) {
                List<Aircraft> tmpAircraft = aircraftDAO.getAllByHangarId(hangar.getId());
                if (tmpAircraft != null) {
                    aircrafts.addAll(tmpAircraft);
                }
                List<Helicopter> tmpHelicopter = helicopterDAO.getAllByHangarId(hangar.getId());
                if (tmpHelicopter != null) {
                    helicopters.addAll(tmpHelicopter);
                }
                List<ArmoredPersonnelCarrier> tmpCarries = carrierDAO.getAllByHangarId(hangar.getId());
                if (tmpCarries != null) {
                    armoredPersonnelCarriers.addAll(tmpCarries);
                }
                List<Tank> tmpTanks = tankDAO.getAllByHangarId(hangar.getId());
                if (tmpTanks != null) {
                    tanks.addAll(tmpTanks);
                }
                List<UAV> tmpUavs = uavDAO.getAllByHangarId(hangar.getId());
                if (tmpUavs != null) {
                    uavs.addAll(tmpUavs);
                }
                List<InfantryFightingVehicle> tmpVehicles = vehicleDAO.getAllByHangarId(hangar.getId());
                if (tmpVehicles != null) {
                    infantryFightingVehicleList.addAll(tmpVehicles);
                }
            }
            for(Jettie jettie : jetties) {
                List<Warship> tmpWarship = warshipDAO.getAllByJettieId(jettie.getId());
                if (tmpWarship != null) {
                    warships.addAll(tmpWarship);
                }
                List<Submarine> tmpSubmarine = submarineDAO.getAllByJettieId(jettie.getId());
                if (tmpSubmarine != null) {
                    submarines.addAll(tmpSubmarine);
                }
                List<Boat> tmpBoat = boatDAO.getAllByJettieId(jettie.getId());
                if (tmpBoat != null) {
                    boats.addAll(tmpBoat);
                }
            }


            for(Soldier soldier : soldiers) {
                barrackStrength += soldier.getStrength();
            }
            for(Commander commander : commanders) {
                barrackStrength += commander.getStrength();
            }
            for(Beginner beginner : beginners) {
                barrackStrength += beginner.getStrength();
            }


            for(Aircraft aircraft : aircrafts) {
                hangarStrength += aircraft.getStrength();
            }
            for(Helicopter helicopter : helicopters) {
                hangarStrength += helicopter.getStrength();
            }
            for(ArmoredPersonnelCarrier carrier : armoredPersonnelCarriers) {
                hangarStrength += carrier.getStrength();
            }
            for(Tank tank : tanks) {
                hangarStrength += tank.getStrength();
            }
            for(UAV uav : uavs) {
                hangarStrength += uav.getStrength();
            }
            for(InfantryFightingVehicle vehicle : infantryFightingVehicleList) {
                hangarStrength += vehicle.getStrength();
            }


            for(Warship warship : warships) {
                jettieStrength += warship.getStrength();
            }
            for(Submarine submarine : submarines) {
                jettieStrength += submarine.getStrength();
            }
            for(Boat boat : boats) {
                jettieStrength += boat.getStrength();
            }

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
        return mainArmyDAO.getRowsCount();
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
            mainArmyDAO.create(newArmy);
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        }
        newArmy.setId(mainArmyDAO.getIdByName(armyName));
        // New army object end

        // barracks start
        logger.info("You need to make some barracks.");
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
            barrack.setNumberOfFloors(getRandomNumber(1, 5));
            barrack.setNumberOfBeds(getRandomNumber(1, 10));
            barrack.setArmyId(newArmy.getId());
            try {
                barrackDAO.create(barrack);
                barrack.setId(barrackDAO.getObjectId(barrack));
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
            int countForSoldiers = getRandomNumber(1, current);
            current -= countForSoldiers;
            int countForCommanders = 0;
            int countForBeginners = 0;
            if (current > 0) {
                countForCommanders = getRandomNumber(1, current);
            }
            current -= countForCommanders;
            if (current > 0) {
                countForBeginners = current;
            }
            current = 0;
            for(int j = 0; j < countForSoldiers; j++) {
                Soldier soldier = new Soldier(getRandomString(), getRandomString(), getRandomString(), barrack.getId());
                try {
                    soldierDAO.create(soldier);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for(int j = 0; j < countForCommanders; j++) {
                Commander commander = new Commander(getRandomString(), getRandomString(), getRandomString(),
                        barrack.getId());
                try {
                    commanderDAO.create(commander);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for(int j = 0; j < countForBeginners; j++) {
                Beginner beginner = new Beginner(getRandomString(), getRandomString(),
                        getRandomDate(BEGIN_LEFT_DATE, BEGIN_RIGHT_DATE), getRandomDate(END_LEFT_DATE, END_RIGHT_DATE),
                        barrack.getId());
                try {
                    beginnerDAO.create(beginner);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        // barracks end

        // hangars start
        logger.info("You need to make some hangars.");
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
            hangar.setNumberOfMilitaryCraft(getRandomNumber(1, 5));
            hangar.setArmyId(newArmy.getId());
            try {
                hangarDAO.create(hangar);
                hangar.setId(hangarDAO.getObjectId(hangar));
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }


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
            int countForAircrafts = getRandomNumber(1, current);
            int countForHelicopters = 0;
            int countForAPCs = 0;
            int countForTanks = 0;
            int countForUAVs = 0;
            int countForIFVs = 0;
            current -= countForAircrafts;
            if (current > 0) {
                countForHelicopters = getRandomNumber(1, current);
            }
            current -= countForHelicopters;
            if (current > 0) {
                countForAPCs = getRandomNumber(1, current);
            }
            current -= countForAPCs;
            if (current > 0) {
                countForTanks = getRandomNumber(1, current);
            }
            current -= countForTanks;
            if (current > 0) {
                countForUAVs = getRandomNumber(1, current);
            }
            current -= countForUAVs;
            if (current > 0) {
                countForIFVs = current;
            }
            current = 0;

            for(int j = 0; j < countForAircrafts; j++) {
                Aircraft aircraft = new Aircraft(getRandomString(), getRandomDate(BEGIN_LEFT_DATE, BEGIN_RIGHT_DATE),
                        getRandomNumber(1, 8), hangar.getId(), getRandomNumber(1, 15));
                try {
                    aircraftDAO.create(aircraft);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for(int j = 0; j < countForHelicopters; j++) {
                Helicopter helicopter = new Helicopter(getRandomString(), getRandomDate(END_LEFT_DATE, END_RIGHT_DATE),
                        getRandomNumber(1, 8), hangar.getId(), getRandomNumber(1, 15));
                try {
                    helicopterDAO.create(helicopter);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForAPCs; j++) {
                ArmoredPersonnelCarrier apc = new ArmoredPersonnelCarrier(getRandomString(),
                        getRandomDate(END_LEFT_DATE, END_RIGHT_DATE), getRandomNumber(1, 8), hangar.getId(),
                        getRandomNumber(0, 7));
                try {
                    carrierDAO.create(apc);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForTanks; j++) {
                Tank tank = new Tank(getRandomString(), getRandomDate(BEGIN_LEFT_DATE, BEGIN_RIGHT_DATE),
                        getRandomNumber(1, 8), hangar.getId(), getRandomNumber(0, 5), getRandomNumber(0, 8));
                try {
                    tankDAO.create(tank);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForUAVs; j++) {
                UAV uav = new UAV(getRandomString(), getRandomDate(BEGIN_LEFT_DATE, BEGIN_RIGHT_DATE),
                        getRandomNumber(1, 8), hangar.getId(), getRandomNumber(0, 10));
                try {
                    uavDAO.create(uav);
                } catch (AttributeNotFoundException e)  {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForIFVs; j++) {
                InfantryFightingVehicle ifv = new InfantryFightingVehicle(getRandomString(),
                        getRandomDate(END_LEFT_DATE, END_RIGHT_DATE), getRandomNumber(1, 8), hangar.getId(),
                        getRandomNumber(0, 5));
                try {
                    vehicleDAO.create(ifv);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        //hangars end

        //jetties start
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
            jettie.setNumberOfShips(getRandomNumber(1, 4));
            jettie.setArmyId(newArmy.getId());
            try {
               jettieDAO.create(jettie);
               jettie.setId(jettieDAO.getObjectId(jettie));
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }


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

            int countForWarships = getRandomNumber(1, current);
            current -= countForWarships;
            int countForSubmarines = 0;
            int countForBoats = 0;
            if (current > 0) {
                countForSubmarines = getRandomNumber(1, current);
            }
            current -= countForSubmarines;
            if (current > 0) {
                countForBoats = getRandomNumber(1, current);
            }
            current = 0;

            for (int j = 0; j < countForWarships; j++) {
                Warship warship = new Warship(getRandomString(), getRandomDate(BEGIN_LEFT_DATE, BEGIN_RIGHT_DATE),
                        getRandomNumber(1, 8), jettie.getId(), getRandomNumber(0, 10), getRandomNumber(1, 15));
                try {
                    warshipDAO.create(warship);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForSubmarines; j++) {
                Submarine submarine = new Submarine(getRandomString(), getRandomDate(END_LEFT_DATE, END_RIGHT_DATE),
                        getRandomNumber(1, 8), jettie.getId(), getRandomNumber(0, 10), getRandomNumber(1, 3));
                try {
                    submarineDAO.create(submarine);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (int j = 0; j < countForBoats; j++) {
                Boat boat = new Boat(getRandomString(), getRandomDate(BEGIN_LEFT_DATE, END_RIGHT_DATE),
                        getRandomNumber(1, 8), jettie.getId(), getRandomNumber(0, 5));
                try {
                    boatDAO.create(boat);
                } catch (AttributeNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        // jetties end
    }

    public static java.sql.Date getRandomDate(java.sql.Date startInclusive, java.sql.Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new java.sql.Date(randomMillisSinceEpoch);
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

    public static List<String> getListOfArmiesNames() {
        return mainArmyDAO.getArmiesNames();
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
