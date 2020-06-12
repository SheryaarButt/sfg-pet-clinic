package com.sharueigo.sfgpetclinic.repositories;

import com.sharueigo.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {

    Owner findByLastName(String lastName);

}
