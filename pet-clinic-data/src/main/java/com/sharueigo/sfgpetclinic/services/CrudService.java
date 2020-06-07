package com.sharueigo.sfgpetclinic.services;

import com.sharueigo.sfgpetclinic.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity> {
    Set<T> findAll();
    T save(T object);
    T findById(Long id);
    void delete(T object);
    void deleteById(Long id);
}
