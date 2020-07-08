package com.sharueigo.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;

    @OneToMany(mappedBy = "owner",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName,
                 String address, String city, String telephone, Set<Pet> pets) {
        super(id,firstName,lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(pets != null){
            this.pets = pets;
        }
    }

    public void addPet(Pet pet){
        if(pet != null){
            pet.setOwner(this);
            pets.add(pet);
        }
    }

    public void removePet(Pet pet){
        if(pet != null){
            pet.setOwner(null);
            pets.remove(pet);
        }
    }

    public Pet getPet(String petName, boolean ignoreNew){
        return pets.stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(petName))
                .filter(pet -> !ignoreNew || !pet.isNew())
                .findFirst().orElse(null);
    }

}
