package com.sharueigo.sfgpetclinic.bootstrap;

import com.sharueigo.sfgpetclinic.model.*;
import com.sharueigo.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final PetService petService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService,
                      PetService petService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.petService = petService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(petTypeService.findAll().size() == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType llama = new PetType();
        llama.setName("Llama");

        PetType giraffe = new PetType();
        giraffe.setName("Giraffe");

        petTypeService.save(dog);
        petTypeService.save(cat);
        petTypeService.save(llama);
        petTypeService.save(giraffe);

        Pet fionasPet = new Pet();
        Pet alexsPet = new Pet();
        fionasPet.setPetType(giraffe);
        alexsPet.setPetType(cat);
        fionasPet.setBirthDate(LocalDate.now());
        alexsPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Spot");
        alexsPet.setName("Rosco");

        Owner owner1 = Owner.builder()
                .firstName("Fiona")
                .lastName("Apple")
                .address("Kingsbridge rd")
                .city("Ellicott")
                .telephone("2134134")
                .build();
        owner1.addPet(fionasPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Alex");
        owner2.setLastName("Briggs");
        owner2.setAddress("super RD");
        owner2.setCity("Denver");
        owner2.setTelephone("8974568");
        owner2.addPet(alexsPet);
        ownerService.save(owner2);

        Visit catVisit = new Visit(LocalDate.now(),"Sneezing",fionasPet);
        visitService.save(catVisit);

        System.out.println("Loaded owners");

        System.out.println("Loading vets...");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");

        specialtyService.save(radiology);
        specialtyService.save(dentistry);
        specialtyService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Gregg");
        vet1.setLastName("Johnson");
        vet1.getSpecialties().add(dentistry);
        vet1.getSpecialties().add(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Linda");
        vet2.setLastName("Yett");
        vet2.getSpecialties().add(radiology);
        vet2.getSpecialties().add(surgery);

        vetService.save(vet2);

        System.out.println("Loaded vets");

        System.out.println("Printing loaded data");
        System.out.println("Owners");
        ownerService.findAll().forEach(System.out::println);
        vetService.findAll().forEach(System.out::println);
        petService.findAll().forEach(System.out::println);
        petTypeService.findAll().forEach(System.out::println);
        visitService.findAll().forEach(System.out::println);
    }
}
