package com.solvd.army.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IBaseDAO<T> {
    void create(T object);
    T getById (long id);
    List<T> getAllRows();
    void update (long id);
    void remove (long id);
}
