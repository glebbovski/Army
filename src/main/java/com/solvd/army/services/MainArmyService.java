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

import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainArmyService {

    public static class AllAboutArmy {
        private MainArmyDAO mainArmyDAO = new MainArmyDAO();
        private BarrackDAO barrackDAO = new BarrackDAO();
        private HangarDAO hangarDAO = new HangarDAO();
        private JettieDAO jettieDAO = new JettieDAO();
        private SoldierDAO soldierDAO = new SoldierDAO();
        private BeginnerDAO beginnerDAO = new BeginnerDAO();
        private CommanderDAO commanderDAO = new CommanderDAO();
        private AircraftDAO aircraftDAO = new AircraftDAO();
        private HelicopterDAO helicopterDAO = new HelicopterDAO();
        private ArmoredPersonnelCarrierDAO carrierDAO = new ArmoredPersonnelCarrierDAO();
        private TankDAO tankDAO = new TankDAO();
        private UAVDAO uavdao = new UAVDAO();
        private InfantryFightingVehicleDAO vehicleDAO = new InfantryFightingVehicleDAO();
        private WarshipDAO warshipDAO = new WarshipDAO();
        private SubmarineDAO submarineDAO = new SubmarineDAO();
        private BoatDAO boatDAO = new BoatDAO();

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
                List<UAV> tmpUavs = uavdao.getAllByHangarId(hangar.getId());
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
                    '}';
        }
    }

    private static MainArmyDAO mainArmyDAO = new MainArmyDAO();

    public static int getCountOfArmies() {
        return mainArmyDAO.getRowsCount();
    }

    public static void addNewArmy() {
        // TODO: code to add new army to MySQL DB
    }

    public static List<String> getListOfArmies() { // names of armies
        return mainArmyDAO.getArmiesNames();
    }
}
