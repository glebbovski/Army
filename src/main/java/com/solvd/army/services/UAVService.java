package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.UAVDAO;
import com.solvd.army.models.hangar.UAV;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class UAVService {
    public static final UAVDAO uavDAO = new UAVDAO();

    public static void addAllUAVs(List<UAV> uavs, List<UAV> tmpUavs) {
        if (tmpUavs != null) {
            uavs.addAll(tmpUavs);
        }
    }

    public static int getStrength(List<UAV> uavs) {
        int tmp = 0;
        for(UAV uav : uavs) {
            tmp += uav.getStrength();
        }
        return tmp;
    }

    public static void addUAVsForHangar(int countForUAVs, long hangarId) {
        for (int j = 0; j < countForUAVs; j++) {
            UAV uav = new UAV(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.BEGIN_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(0, 10));
            try {
                UAVService.uavDAO.create(uav);
            } catch (AttributeNotFoundException e)  {
                e.printStackTrace();
            }
        }
    }
}
