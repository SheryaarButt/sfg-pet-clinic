package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.services.PetService;
import com.sharueigo.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends CrudServiceMap<Pet> implements PetService {

    private final PetTypeService petTypeService;

    public PetServiceMap(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet save(Pet pet){
        if(pet != null){
            if(pet.getPetType() != null){
                if(pet.getPetType().getId() == null){
                    petTypeService.save(pet.getPetType());
                    System.out.println(pet.getPetType().getName() + " saved with ID " + pet.getPetType().getId());
                }
            } else {
                throw new RuntimeException("Pet requires pet type");
            }
        }
        return super.save(pet);
    }
}
