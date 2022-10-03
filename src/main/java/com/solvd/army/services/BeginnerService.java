package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.barrack.BeginnerDAO;
import com.solvd.army.models.barrack.Beginner;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class BeginnerService {
    public static final BeginnerDAO beginnerDAO = new BeginnerDAO();

    public static void addAllBeginners(List<Beginner> beginners, List<Beginner> lstBeginner) {
        if (lstBeginner != null) {
            beginners.addAll(lstBeginner);
        }
    }

    public static int getStrength(List<Beginner> beginners) {
        int tmp = 0;
        for(Beginner beginner : beginners) {
            tmp += beginner.getStrength();
        }
        return tmp;
    }

    public static void addBeginnersForBarrack(int countForBeginners, long barrackId) {
        for(int j = 0; j < countForBeginners; j++) {
            Beginner beginner = new Beginner(MainService.getRandomString(), MainService.getRandomString(),
                    MainService.getRandomDate(MainArmyService.BEGIN_LEFT_DATE, MainArmyService.BEGIN_RIGHT_DATE),
                    MainService.getRandomDate(MainArmyService.END_LEFT_DATE, MainArmyService.END_RIGHT_DATE),
                    barrackId);
            try {
                BeginnerService.beginnerDAO.create(beginner);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
