package com.solvd.army.dao;

import com.solvd.army.models.hangar.Hangar;

import java.util.List;

public interface IHangarDAO extends IBaseDAO<Hangar>{
    List<Hangar> getAllHangarsByArmyId(long id);
}
