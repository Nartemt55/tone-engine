package ru.nartemt.tone_engine_ver2.service.base;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<E, ID, R extends JpaRepository<E, ID> & JpaSpecificationExecutor<E>>
    implements BaseService<E, ID> {

    protected final R repository;

    protected AbstractBaseService(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ID id) {
        repository.delete(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

}
