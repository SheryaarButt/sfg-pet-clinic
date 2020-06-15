package com.sharueigo.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
