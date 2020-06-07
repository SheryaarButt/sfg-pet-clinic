package com.sharueigo.sfgpetclinic.services.map;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.services.OwnerService;

import java.util.Map;

public class OwnerServiceMap extends BaseServiceMap<Owner> implements OwnerService {

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

}
