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
import java.util.ArrayList;
import java.util.List;

public class MainArmyService {

    private static final Logger logger = LogManager.getLogger(MainArmyService.class);

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
    private static UAVDAO uavdao = new UAVDAO();
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

    public static void addNewArmy() {
        // TODO: code to add new army to MySQL DB
    }

    public static List<String> getListOfArmies() { // names of armies
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
