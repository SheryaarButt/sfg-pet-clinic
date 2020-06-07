package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.BaseEntity;
import com.sharueigo.sfgpetclinic.services.CrudService;

import java.util.*;

public class BaseServiceMap<T extends BaseEntity> implements CrudService<T> {

    protected Map<Long,T> map = new HashMap<>();

    @Override
    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    @Override
    public T save(T object){
        if(object != null){
            object.setId(nextId());
            map.put(object.getId(),object);
        } else {
            throw new RuntimeException("Can't save null object");
        }
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

    private Long nextId(){
        if(map.isEmpty()){
            return 1L;
        } else {
            return (Collections.max(map.keySet()) + 1);
        }
    }

}
