package com.solvd.army.dao;

import com.solvd.army.models.hangar.Aircraft;

import javax.management.AttributeNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
