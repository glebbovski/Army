package com.solvd.army.dao;

import com.solvd.army.models.hangar.Helicopter;

import java.util.List;

public interface IHelicopterDAO extends IBaseDAO<Helicopter>{
    List<Helicopter> getAllByHangarId(long id);
}
