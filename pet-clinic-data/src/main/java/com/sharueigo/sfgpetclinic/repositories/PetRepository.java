package com.sharueigo.sfgpetclinic.repositories;

import com.sharueigo.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
