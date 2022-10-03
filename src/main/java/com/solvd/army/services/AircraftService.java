package com.solvd.army.services;


import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.hangar.Aircraft;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class AircraftService {
    public static final AircraftDAO aircraftDAO = new AircraftDAO();

    public static void addAllAircrafts(List<Aircraft> aircrafts, List<Aircraft> tmpAircraft) {
        if (tmpAircraft != null) {
            aircrafts.addAll(tmpAircraft);
        }
    }

    public static int getStrength(List<Aircraft> aircrafts) {
        int tmp = 0;
        for(Aircraft aircraft : aircrafts) {
            tmp += aircraft.getStrength();
        }
        return tmp;
    }

    public static void addAircraftsForHangar(int countForAircrafts, long hangarId) {
        for(int j = 0; j < countForAircrafts; j++) {
            Aircraft aircraft = new Aircraft(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.BEGIN_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(1, 15));
            try {
                AircraftService.aircraftDAO.create(aircraft);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
