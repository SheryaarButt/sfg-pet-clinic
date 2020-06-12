package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.BaseEntity;
import com.sharueigo.sfgpetclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class CrudServiceJpa<T extends BaseEntity> implements CrudService<T> {

    private final CrudRepository<T,Long> crudRepository;

    public CrudServiceJpa(CrudRepository<T, Long> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Set<T> findAll() {

        Set<T> objects = new HashSet<>();

        crudRepository.findAll().forEach(objects::add);

        return objects;
    }

    @Override
    public T save(T object) {
        return crudRepository.save(object);
    }

    @Override
    public T findById(Long id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(T object) {
        crudRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }
}
