package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.HelicopterDAO;
import com.solvd.army.models.hangar.Helicopter;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class HelicopterService {
    public static final HelicopterDAO helicopterDAO = new HelicopterDAO();

    public static void addAllHelicopters(List<Helicopter> helicopters, List<Helicopter> tmpHelicopter) {
        if (tmpHelicopter != null) {
            helicopters.addAll(tmpHelicopter);
        }
    }

    public static int getStrength(List<Helicopter> helicopters) {
        int tmp = 0;
        for(Helicopter helicopter : helicopters) {
            tmp += helicopter.getStrength();
        }
        return tmp;
    }

    public static void addHelicoptersForHangar(int countForHelicopters, long hangarId) {
        for(int j = 0; j < countForHelicopters; j++) {
            Helicopter helicopter = new Helicopter(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.END_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(1, 15));
            try {
                HelicopterService.helicopterDAO.create(helicopter);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
