package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.PetType;
import com.sharueigo.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class PetTypeServiceMap extends CrudServiceMap<PetType> implements PetTypeService {
}
