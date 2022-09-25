package com.solvd.army.dao;

import com.solvd.army.models.hangar.Aircraft;

import java.util.List;

public interface IAircraftDAO extends IBaseDAO<Aircraft>{
    List<Aircraft> getAllByHangarId(long id);
}
