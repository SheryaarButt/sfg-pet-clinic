package com.sharueigo.sfgpetclinic.repositories;

import com.sharueigo.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner,Long> {

    Owner findByLastName(String lastName);
    Set<Owner> findByLastNameIgnoreCaseContaining(String lastName);

}
