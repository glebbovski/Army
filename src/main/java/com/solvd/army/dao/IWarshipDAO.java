package com.solvd.army.dao;

import com.solvd.army.models.jettie.Warship;

import java.util.List;

public interface IWarshipDAO extends IBaseDAO<Warship>{
    List<Warship> getAllByJettieId(long id);;
}
