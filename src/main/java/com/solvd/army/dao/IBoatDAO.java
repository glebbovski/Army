package com.solvd.army.dao;

import com.solvd.army.models.jettie.Boat;

import java.util.List;

public interface IBoatDAO extends IBaseDAO<Boat>{
    List<Boat> getAllByJettieId(long id);
}
