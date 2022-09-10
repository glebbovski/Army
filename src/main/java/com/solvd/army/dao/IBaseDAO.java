package com.solvd.army.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface IBaseDAO<T> {
    String DRIVER_NAME = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/ca";
    String ID = "root";
    String PASS = "";

    void create(T object);
    T getById (long id);
    void update (long id);
    void remove (long id);

    default Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    default void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    default void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
