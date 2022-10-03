package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.barrack.CommanderDAO;
import com.solvd.army.models.barrack.Commander;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class CommanderService {
    public static final CommanderDAO commanderDAO = new CommanderDAO();

    public static void addAllCommanders(List<Commander> commanders, List<Commander> lstCommander) {
        if (lstCommander != null) {
            commanders.addAll(lstCommander);
        }
    }

    public static int getStrength(List<Commander> commanders) {
        int tmp = 0;
        for(Commander commander : commanders) {
            tmp += commander.getStrength();
        }
        return tmp;
    }

    public static void addCommandersForBarrack(int countForCommanders, long barrackId) {
        for(int j = 0; j < countForCommanders; j++) {
            Commander commander = new Commander(MainService.getRandomString(), MainService.getRandomString(),
                    MainService.getRandomString(), barrackId);
            try {
                CommanderService.commanderDAO.create(commander);
            } catch (AttributeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
