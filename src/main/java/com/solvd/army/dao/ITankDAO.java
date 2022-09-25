package com.solvd.army.dao;

import com.solvd.army.models.hangar.Tank;

import java.util.List;

public interface ITankDAO extends IBaseDAO<Tank>{
    List<Tank> getAllByHangarId(long id);
}
