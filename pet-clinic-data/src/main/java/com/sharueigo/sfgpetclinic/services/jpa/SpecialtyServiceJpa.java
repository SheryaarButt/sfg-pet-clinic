package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Specialty;
import com.sharueigo.sfgpetclinic.repositories.SpecialtyRepository;
import com.sharueigo.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"jpa","sql"})
public class SpecialtyServiceJpa extends CrudServiceJpa<Specialty> implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceJpa(SpecialtyRepository specialtyRepository) {
        super(specialtyRepository);
        this.specialtyRepository = specialtyRepository;
    }
}
