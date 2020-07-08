package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.model.Visit;
import com.sharueigo.sfgpetclinic.services.PetService;
import com.sharueigo.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/owners/{ownerId:[1-9]\\d*}/pets/{petId:[1-9]\\d*}/visits")
public class VisitController {

    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder({"pet","visit"})
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("pet")
    public Pet setPet(@PathVariable Long petId){
        return petService.findById(petId);
    }

    @GetMapping("/new")
    public ModelAndView initializeNewForm(Pet pet){
        return new ModelAndView("owners/pets/createOrUpdateVisitForm")
                    .addObject(pet)
                    .addObject(new Visit());
    }

    @PostMapping("/new")
    public ModelAndView processNewForm(@Validated Visit visit, Pet pet, BindingResult result,
                                       @PathVariable Long ownerId){
        if(result.hasErrors()){
            return new ModelAndView("owners/pets/createOrUpdateVisitForm")
                        .addObject(pet)
                        .addObject(visit);
        }
        visit.setPet(pet);
        visitService.save(visit);
        return new ModelAndView("redirect:/owners/" + ownerId);
    }

    @GetMapping("/{visitId:[1-9]\\d*}/edit")
    public ModelAndView initializeEditForm(Pet pet, @PathVariable Long visitId){
        return new ModelAndView("owners/pets/createOrUpdateVisitForm")
                    .addObject(pet)
                    .addObject(visitService.findById(visitId));
    }

    @PostMapping("/{visitId:[1-9]\\d*}/edit")
    public ModelAndView processEditForm(){
        return null;
    }

}
