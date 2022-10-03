package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.jettie.WarshipDAO;
import com.solvd.army.models.jettie.Warship;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class WarshipService {
    public static final WarshipDAO warshipDAO = new WarshipDAO();

    public static void addAllWarships(List<Warship> warships, List<Warship> tmpWarship) {
        if (tmpWarship != null) {
            warships.addAll(tmpWarship);
        }
    }

    public static int getStrength(List<Warship> warships) {
        int tmp = 0;
        for(Warship warship : warships) {
            tmp += warship.getStrength();
        }
        return tmp;
    }

    public static void addWarshipsForJettie(int countForWarships, long jettieId) {
        for (int j = 0; j < countForWarships; j++) {
            Warship warship = new Warship(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.BEGIN_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), jettieId, MainService.getRandomNumber(0, 10),
                    MainService.getRandomNumber(1, 15));
            try {
                WarshipService.warshipDAO.create(warship);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
