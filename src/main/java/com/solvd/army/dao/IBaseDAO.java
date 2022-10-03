package com.solvd.army.dao;

import javax.management.AttributeNotFoundException;
import java.util.List;

public interface IBaseDAO<T> {
    void create(T object) throws AttributeNotFoundException;
    T getById (long id);
    List<T> getAllRows();
    void update (long id);
    void update (T object);
    void remove (long id);
    long getObjectId(T object) throws AttributeNotFoundException;

}
