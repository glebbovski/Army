package com.solvd.army.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IBaseDAO<T> {
//    String DRIVER_NAME = "com.mysql.jdbc.Driver";
//    String DB_URL = "jdbc:mysql://hostname:3306/army";
//    String ID = "root";
//    String PASS = "";

    void create(T object);
    T getById (long id);
    List<T> getAllRows();
    void update (long id);
    void remove (long id);

}
