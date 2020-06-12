package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.PetType;
import com.sharueigo.sfgpetclinic.repositories.PetTypeRepository;
import com.sharueigo.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class PetTypeServiceJpa extends CrudServiceJpa<PetType> implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceJpa(PetTypeRepository petTypeRepository) {
        super(petTypeRepository);
        this.petTypeRepository = petTypeRepository;
    }

}
