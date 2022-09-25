package com.solvd.army.dao;

import com.solvd.army.models.hangar.InfantryFightingVehicle;

import java.util.List;

public interface IInfantryFightingVehicleDAO extends IBaseDAO<InfantryFightingVehicle>{
    List<InfantryFightingVehicle> getAllByHangarId(long id);

}
