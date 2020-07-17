package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.repositories.OwnerRepository;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"jpa","sql"})
public class OwnerServiceJpa extends CrudServiceJpa<Owner> implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceJpa(OwnerRepository ownerRepository) {
        super(ownerRepository);
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findByLastNameIgnoreCaseContaining(String lastName) {
        return ownerRepository.findByLastNameIgnoreCaseContaining(lastName);
    }
}
