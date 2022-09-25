package com.solvd.army.dao;

import com.solvd.army.models.hangar.ArmoredPersonnelCarrier;

import java.util.List;

public interface IArmoredPersonnelCarrierDAO extends IBaseDAO<ArmoredPersonnelCarrier>{
    List<ArmoredPersonnelCarrier> getAllByHangarId(long id);

}
