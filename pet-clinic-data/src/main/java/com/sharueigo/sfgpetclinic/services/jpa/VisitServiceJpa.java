package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Visit;
import com.sharueigo.sfgpetclinic.repositories.VisitRepository;
import com.sharueigo.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("jpa")
public class VisitServiceJpa extends CrudServiceJpa<Visit> implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceJpa(VisitRepository visitRepository) {
        super(visitRepository);
        this.visitRepository = visitRepository;
    }
}
