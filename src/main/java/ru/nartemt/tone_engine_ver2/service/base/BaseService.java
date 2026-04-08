package ru.nartemt.tone_engine_ver2.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID> {
    Optional<E> findById(ID id);
    List<E> findAll();
    E save(E entity);
    void delete(ID id);
}
