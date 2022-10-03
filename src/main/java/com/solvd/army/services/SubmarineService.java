package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.jettie.SubmarineDAO;
import com.solvd.army.models.jettie.Submarine;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class SubmarineService {
    public static final SubmarineDAO submarineDAO = new SubmarineDAO();

    public static void addAllSubmarines(List<Submarine> submarines, List<Submarine> tmpSubmarine) {
        if (tmpSubmarine != null) {
            submarines.addAll(tmpSubmarine);
        }
    }

    public static int getStrength(List<Submarine> submarines) {
        int tmp = 0;
        for(Submarine submarine : submarines) {
            tmp += submarine.getStrength();
        }
        return tmp;
    }

    public static void addSubmarinesForJettie(int countForSubmarines, long jettieId) {
        for (int j = 0; j < countForSubmarines; j++) {
            Submarine submarine = new Submarine(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.END_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), jettieId,
                    MainService.getRandomNumber(0, 10), MainService.getRandomNumber(1, 3));
            try {
                SubmarineService.submarineDAO.create(submarine);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
