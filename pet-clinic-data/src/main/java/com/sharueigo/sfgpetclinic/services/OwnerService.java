package com.sharueigo.sfgpetclinic.services;


import com.sharueigo.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long> {

    Owner findByLastName(String lastName);
}
