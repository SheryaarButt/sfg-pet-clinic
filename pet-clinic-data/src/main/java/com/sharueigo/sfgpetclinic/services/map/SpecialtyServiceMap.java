package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.Specialty;
import com.sharueigo.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class SpecialtyServiceMap extends CrudServiceMap<Specialty> implements SpecialtyService {

}
