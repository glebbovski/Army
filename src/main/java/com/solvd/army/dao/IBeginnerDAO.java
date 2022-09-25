package com.solvd.army.dao;

import com.solvd.army.models.barrack.Beginner;

import java.util.List;

public interface IBeginnerDAO extends IBaseDAO<Beginner>{
    List<Beginner> getAllByBarrackId(long id);
}
