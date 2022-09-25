package com.solvd.army.dao;

import com.solvd.army.models.barrack.Barrack;

import java.util.List;

public interface IBarrackDAO extends IBaseDAO<Barrack>{
    List<Barrack> getAllBarracksByArmyId(long id);
}
