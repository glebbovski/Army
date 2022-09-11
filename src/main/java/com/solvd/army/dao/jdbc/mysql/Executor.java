package com.solvd.army.dao.jdbc.mysql;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.MainArmy;

public class Executor {
    public static void main(String[] args) {
        IBaseDAO<MainArmy> armyIBaseDAO = new MainArmyDAO();
        System.out.println(armyIBaseDAO.getById(1).toString());
    }
}
