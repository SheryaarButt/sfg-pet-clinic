package com.sharueigo.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "vets")
    private Set<Specialty> specialties = new HashSet<>();

    @Override
    public String toString() {
        return "Vet{" +
                "specialties=" + specialties +
                '}';
    }
}
