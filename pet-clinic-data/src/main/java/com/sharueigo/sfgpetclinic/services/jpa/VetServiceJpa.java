package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Vet;
import com.sharueigo.sfgpetclinic.repositories.VetRepository;
import com.sharueigo.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class VetServiceJpa extends CrudServiceJpa<Vet> implements VetService {

    private final VetRepository vetRepository;

    public VetServiceJpa(VetRepository vetRepository) {
        super(vetRepository);
        this.vetRepository = vetRepository;
    }
}
