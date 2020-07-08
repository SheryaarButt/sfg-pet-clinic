package com.sharueigo.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String description;

    @ManyToOne
    private Pet pet = new Pet();

    @Builder
    public Visit(Long id, LocalDate date, String description, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        if(pet != null){
            this.pet = pet;
        }
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", pet=" + pet.getName() +
                '}';
    }
}
