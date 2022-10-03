package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.ArmoredPersonnelCarrierDAO;
import com.solvd.army.models.hangar.ArmoredPersonnelCarrier;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class APCService {
    public static final ArmoredPersonnelCarrierDAO carrierDAO = new ArmoredPersonnelCarrierDAO();

    public static void addAllAPCs(List<ArmoredPersonnelCarrier> armoredPersonnelCarriers,
                                  List<ArmoredPersonnelCarrier> tmpCarries) {
        if (tmpCarries != null) {
            armoredPersonnelCarriers.addAll(tmpCarries);
        }
    }

    public static int getStrength(List<ArmoredPersonnelCarrier> armoredPersonnelCarriers) {
        int tmp = 0;
        for(ArmoredPersonnelCarrier carrier : armoredPersonnelCarriers) {
            tmp += carrier.getStrength();
        }
        return tmp;
    }

    public static void addAPCsForHangar(int countForAPCs, long hangarId) {
        for (int j = 0; j < countForAPCs; j++) {
            ArmoredPersonnelCarrier apc = new ArmoredPersonnelCarrier(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.END_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(0, 7));
            try {
                APCService.carrierDAO.create(apc);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
