package com.sharueigo.sfgpetclinic.services;

import com.sharueigo.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet save(Pet pet);

    Pet findById(Long id);

    Set<Pet> findAll();

}
