package com.solvd.army.dao;

import com.solvd.army.models.barrack.Commander;

import java.util.List;

public interface ICommanderDAO extends IBaseDAO<Commander>{
    List<Commander> getAllByBarrackId(long id);
}
