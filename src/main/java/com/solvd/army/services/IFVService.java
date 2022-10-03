package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.hangar.InfantryFightingVehicleDAO;
import com.solvd.army.models.hangar.InfantryFightingVehicle;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class IFVService {
    public static final InfantryFightingVehicleDAO vehicleDAO = new InfantryFightingVehicleDAO();

    public static void addAllIFVs(List<InfantryFightingVehicle> infantryFightingVehicleList,
                                  List<InfantryFightingVehicle> tmpVehicles) {
        if (tmpVehicles != null) {
            infantryFightingVehicleList.addAll(tmpVehicles);
        }
    }

    public static int getStrength(List<InfantryFightingVehicle> infantryFightingVehicleList) {
        int tmp = 0;
        for(InfantryFightingVehicle vehicle : infantryFightingVehicleList) {
            tmp += vehicle.getStrength();
        }
        return tmp;
    }

    public static void addIFVsForHangar(int countForIFVs, long hangarId) {
        for (int j = 0; j < countForIFVs; j++) {
            InfantryFightingVehicle ifv = new InfantryFightingVehicle(MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.END_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    MainService.getRandomNumber(1, 8), hangarId, MainService.getRandomNumber(0, 5));
            try {
                IFVService.vehicleDAO.create(ifv);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
