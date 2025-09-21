package com.solvd.atm.dao.interfaces;

import java.util.List;

public interface IGenericDAO<T> {

    T getById(int id) throws Exception;

    List<T> getAll() throws Exception;

    void insert(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(int id) throws Exception;
}
