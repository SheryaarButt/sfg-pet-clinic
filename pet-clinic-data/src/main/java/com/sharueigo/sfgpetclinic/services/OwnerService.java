package com.sharueigo.sfgpetclinic.services;


import com.sharueigo.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner> {
    Owner findByLastName(String lastName);
    Set<Owner> findByLastNameIgnoreCaseContaining(String lastName);
}
