package com.solvd.army.dao;

import com.solvd.army.models.jettie.Jettie;

import java.util.List;

public interface IJettieDAO extends IBaseDAO<Jettie>{
    List<Jettie> getAllJettiesByArmyId(long id);
}
