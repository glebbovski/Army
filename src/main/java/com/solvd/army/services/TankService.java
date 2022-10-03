package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.TankDAO;
import com.solvd.army.models.hangar.Tank;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class TankService {
    public static final TankDAO tankDAO = new TankDAO();

    public static void addAllTanks(List<Tank> tanks, List<Tank> tmpTanks) {
        if (tmpTanks != null) {
            tanks.addAll(tmpTanks);
        }
    }

    public static int getStrength(List<Tank> tanks) {
        int tmp = 0;
        for(Tank tank : tanks) {
            tmp += tank.getStrength();
        }
        return tmp;
    }

    public static void addTanksForHangar(int countForTanks, long hangarId) {
        for (int j = 0; j < countForTanks; j++) {
            Tank tank = new Tank(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.BEGIN_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(0, 5),
                    MainService.getRandomNumber(0, 8));
            try {
                TankService.tankDAO.create(tank);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
