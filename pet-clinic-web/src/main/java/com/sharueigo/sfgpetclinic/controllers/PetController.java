package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.model.PetType;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import com.sharueigo.sfgpetclinic.services.PetService;
import com.sharueigo.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners/{ownerId:[1-9]\\d*}/pets")
@Controller
public class PetController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @InitBinder({"owner","pet"})
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Set<PetType> setTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner setOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @GetMapping("/new")
    public ModelAndView newPetForm(Owner owner){
        Pet newPet = new Pet();
        owner.addPet(newPet);
        return new ModelAndView("owners/pets/createOrUpdatePetForm").addObject("pet",newPet);
    }

    @PostMapping("/new")
    public ModelAndView newPetAction(Owner owner, @Validated Pet pet, BindingResult result){
        pet.setOwner(owner);
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name","duplicateName","pet name already exists");
        }
        if(result.hasErrors()){
            return new ModelAndView("owners/pets/createOrUpdatePetForm").addObject("pet",pet);
        }
        petService.save(pet);
        return new ModelAndView("redirect:/owners/" + owner.getId());
    }

    @GetMapping("/{petId:[1-9]\\d*}/edit")
    public ModelAndView editPetForm(@PathVariable Long petId){
        return new ModelAndView("owners/pets/createOrUpdatePetForm").addObject("pet",petService.findById(petId));
    }

    @PostMapping("/{petId:[1-9]\\d*}/edit")
    public ModelAndView editPetAction(Owner owner,@PathVariable Long petId, Pet pet, BindingResult result){
        pet.setId(petId);
        pet.setOwner(owner);
        if(result.hasErrors()){
            return new ModelAndView("owners/pets/createOrUpdatePetForm").addObject("pet",pet);
        }
        petService.save(pet);
        return new ModelAndView("redirect:/owners/" + owner.getId());
    }



}
