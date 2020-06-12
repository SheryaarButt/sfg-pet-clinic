package com.sharueigo.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owner owner;

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pet",
               cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();

    private LocalDate birthDate;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit visit){
        if(visit != null){
            visit.setPet(this);
            visits.add(visit);
        }
    }

    public void removeVisit(Visit visit){
        if(visit != null){
            visit.setPet(null);
            visits.remove(visit);
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petType=" + petType +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                '}';
    }
}
