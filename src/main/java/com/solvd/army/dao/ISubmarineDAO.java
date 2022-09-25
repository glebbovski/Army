package com.solvd.army.dao;

import com.solvd.army.models.jettie.Submarine;

import java.util.List;

public interface ISubmarineDAO extends IBaseDAO<Submarine>{
    List<Submarine> getAllByJettieId(long id);
}
