package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.BaseEntity;
import com.sharueigo.sfgpetclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaseServiceMap<T extends BaseEntity> implements CrudService<T> {

    protected Map<Long,T> map = new HashMap<>();

    @Override
    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    @Override
    public T save(T object){
        map.put(object.getId(),object);
        return object;
    }

    @Override
    public T findById(Long id){
        return map.get(id);
    }

    @Override
    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteById(Long id){
        map.remove(id);
    }

}
