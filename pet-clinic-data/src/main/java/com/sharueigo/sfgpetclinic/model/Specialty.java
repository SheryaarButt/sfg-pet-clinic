package com.sharueigo.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

    private String description;

    @ManyToMany
    @JoinTable(name = "specialty_vets",
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "vet_id"))
    private Set<Vet> vets = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Vet> getVets() {
        return vets;
    }

    public void setVets(Set<Vet> vets) {
        this.vets = vets;
    }
}
