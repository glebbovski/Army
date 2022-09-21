package com.solvd.army.services;

import com.solvd.army.dao.jdbc.mysql.MainArmyDAO;
import com.solvd.army.models.MainArmy;

import java.util.List;

public class MainArmyService {

    private static MainArmyDAO mainArmyDAO = new MainArmyDAO();

    public static int getCountOfArmies() {
        return mainArmyDAO.getRowsCount();
    }

    public static void addNewArmy() {
        // TODO: code to add new army to MySQL DB
    }

    public static List<String> getListOfArmies() { // names of armies
        return mainArmyDAO.getArmiesNames();
    }
}
