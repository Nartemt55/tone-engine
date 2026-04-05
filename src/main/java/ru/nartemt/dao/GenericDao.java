package ru.nartemt.dao;

import java.util.List;

public interface GenericDao<T> {

    T findById(long id);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
}
