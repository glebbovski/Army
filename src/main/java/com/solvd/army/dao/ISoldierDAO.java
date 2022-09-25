package com.solvd.army.dao;

import com.solvd.army.models.barrack.Soldier;

import java.util.List;

public interface ISoldierDAO extends IBaseDAO<Soldier>{
    List<Soldier> getAllByBarrackId(long id);
}
