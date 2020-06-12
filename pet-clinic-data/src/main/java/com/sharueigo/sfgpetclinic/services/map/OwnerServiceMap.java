package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import com.sharueigo.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OwnerServiceMap extends CrudServiceMap<Owner> implements OwnerService {

    private final PetService petService;

    public OwnerServiceMap(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Owner findByLastName(String lastName){
        for(Map.Entry<Long,Owner> entry : this.map.entrySet()){
            if(entry.getValue().getLastName().equalsIgnoreCase(lastName)){
                return entry.getValue();
            }
        }
        //not found
        return null;
    }

    @Override
    public Owner save(Owner owner){
        if(owner != null){
            if(!owner.getPets().isEmpty()){
                owner.getPets().forEach(pet -> {
                    pet.setOwner(owner);
                    System.out.println(pet.getName() + " attached " + owner.getFirstName() + " as owner");
                    if(pet.getId() == null){
                        petService.save(pet);
                        System.out.println(pet.getName() + " saved with ID " + pet.getId());
                    }
                });
            } else {
                throw new RuntimeException("Owner requires pets");
            }
        }
        return super.save(owner);
    }

}
