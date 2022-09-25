package com.solvd.army.dao;

import com.solvd.army.models.hangar.UAV;

import java.util.List;

public interface IUAVDAO extends IBaseDAO<UAV>{
    List<UAV> getAllByHangarId(long id);
}
