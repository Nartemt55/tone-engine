package ru.nartemt.tone_engine_ver2.dao;

import java.util.List;

public interface GenericDao<T> {

    T findById(long id);
    List<T> findAll();
    void save(T entity);
    void delete(long id);
}
