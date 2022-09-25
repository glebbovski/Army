package com.solvd.army.dao;

import com.solvd.army.models.MainArmy;

import java.util.List;

public interface IMainArmyDAO extends IBaseDAO<MainArmy>{
    int getRowsCount();
    List<String> getArmiesNames();
    long getIdByName(String name);
}
