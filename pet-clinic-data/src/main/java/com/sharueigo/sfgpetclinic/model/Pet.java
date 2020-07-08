package com.sharueigo.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String name;

    @Builder
    public Pet(Long id, PetType petType, Owner owner, Set<Visit> visits,
               LocalDate birthDate, String name) {
        super(id);
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.name = name;
        if(visits != null){
            this.visits = visits;
        }
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
