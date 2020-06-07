package com.sharueigo.sfgpetclinic.bootstrap;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.model.Vet;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import com.sharueigo.sfgpetclinic.services.VetService;
import com.sharueigo.sfgpetclinic.services.map.OwnerServiceMap;
import com.sharueigo.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading owners...");

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Fiona");
        owner1.setLastName("Apple");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Alex");
        owner2.setLastName("Briggs");
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        System.out.println("Loading vets...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Gregg");
        vet1.setLastName("Johnson");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Linda");
        vet2.setLastName("Yett");
        vetService.save(vet2);

        System.out.println("Loaded vets");

    }
}
