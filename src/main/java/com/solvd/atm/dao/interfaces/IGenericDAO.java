package com.solvd.atm.dao.interfaces;

import java.util.List;

public interface IGenericDAO<T, K> {

    T findById(K id) throws Exception;

    List<T> findAll() throws Exception;

    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(K id) throws Exception;
}
