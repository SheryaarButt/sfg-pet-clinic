package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.repositories.PetRepository;
import com.sharueigo.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"jpa","sql"})
public class PetServiceJpa extends CrudServiceJpa<Pet> implements PetService {

    public PetServiceJpa(PetRepository petRepository) {
        super(petRepository);
    }

}
