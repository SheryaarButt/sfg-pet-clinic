package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.Vet;
import com.sharueigo.sfgpetclinic.services.SpecialtyService;
import com.sharueigo.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class VetServiceMap extends CrudServiceMap<Vet> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet vet){
        if(vet != null){
            if(!vet.getSpecialties().isEmpty()){
                vet.getSpecialties().forEach(specialty -> {
                    if(specialty.getId() == null){
                        specialtyService.save(specialty);
                        System.out.println(specialty.getDescription() + " given the ID: " + specialty.getId());
                    }
                });
            } else {
                throw new RuntimeException("Vet requires at least one specialty");
            }
        }
        return super.save(vet);
    }

}
