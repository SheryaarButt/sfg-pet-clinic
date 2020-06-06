package com.sharueigo.sfgpetclinic.services;

import com.sharueigo.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet save(Vet vet);

    Vet findById(Long id);

    Set<Vet> findAll();

}
