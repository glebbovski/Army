package com.solvd.army.dao;

import java.sql.Connection;

public interface IBaseDAO<T> {
    void create(T object);
    T getById (long id);
    void update (long id);
    void remove (long id);
}
