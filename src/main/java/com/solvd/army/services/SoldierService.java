package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.barrack.SoldierDAO;
import com.solvd.army.models.barrack.Soldier;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class SoldierService {
    public static final SoldierDAO soldierDAO = new SoldierDAO();

    public static void addAllSoldiers(List<Soldier> soldiers, List<Soldier> lstSoldier) {
        if (lstSoldier != null) {
            soldiers.addAll(lstSoldier);
        }
    }

    public static int getStrength(List<Soldier> soldiers) {
        int tmp = 0;
        for(Soldier soldier : soldiers) {
            tmp += soldier.getStrength();
        }
        return tmp;
    }

    public static void addSoldiersForBarrack(int countForSoldiers, long barrackId) {
        for(int j = 0; j < countForSoldiers; j++) {
            Soldier soldier = new Soldier(MainService.getRandomString(), MainService.getRandomString(),
                    MainService.getRandomString(), barrackId);
            try {
                SoldierService.soldierDAO.create(soldier);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
