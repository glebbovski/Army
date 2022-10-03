package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.jettie.BoatDAO;
import com.solvd.army.models.jettie.Boat;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class BoatService {
    public static final BoatDAO boatDAO = new BoatDAO();

    public static void addAllBoats(List<Boat> boats, List<Boat> tmpBoat) {
        if (tmpBoat != null) {
            boats.addAll(tmpBoat);
        }
    }

    public static int getStrength(List<Boat> boats) {
        int tmp = 0;
        for(Boat boat : boats) {
            tmp += boat.getStrength();
        }
        return tmp;
    }

    public static void addBoatsForJettie(int countForBoats, long jettieId) {
        for (int j = 0; j < countForBoats; j++) {
            Boat boat = new Boat(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), jettieId, MainService.getRandomNumber(0, 5));
            try {
                BoatService.boatDAO.create(boat);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
