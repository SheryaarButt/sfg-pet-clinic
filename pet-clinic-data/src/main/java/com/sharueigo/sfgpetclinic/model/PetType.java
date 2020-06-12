package com.sharueigo.sfgpetclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "types")
public class PetType extends BaseEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PetType{" +
                "name='" + name + '\'' +
                '}';
    }
}
