package ru.nartemt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class EntityDao<T> implements GenericDao<T> {

    protected final Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findById(long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz).getResultList();
    }


    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }


    @Override
    public void delete(long id) {
        T entity = findById(id);
        if (entity != null)
            entityManager.remove(entity);
    }
}
