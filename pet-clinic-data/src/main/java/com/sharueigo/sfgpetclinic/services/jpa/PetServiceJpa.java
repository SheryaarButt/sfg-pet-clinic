package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.repositories.PetRepository;
import com.sharueigo.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class PetServiceJpa extends CrudServiceJpa<Pet> implements PetService {

    private final PetRepository petRepository;

    public PetServiceJpa(PetRepository petRepository) {
        super(petRepository);
        this.petRepository = petRepository;
    }
}