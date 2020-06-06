package com.sharueigo.sfgpetclinic.services;


import com.sharueigo.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner save(Owner owner);

    Owner findById(Long id);

    Set<Owner> findAll();

}
