package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find","/find.html"})
    public ModelAndView findOwners(){
        ModelAndView modelAndView = new ModelAndView("owners/findOwners");
        modelAndView.addObject(new Owner());
        return modelAndView;
    }

    @GetMapping("/{ownerId:[1-9]\\d*}")
    public ModelAndView ownerDetails(@PathVariable Long ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }

    @GetMapping
    public ModelAndView findOwnersAction(Owner owner, BindingResult result){
        Set<Owner> foundOwners = ownerService.findByLastNameIgnoreCaseContaining(owner.getLastName());
        if(foundOwners.isEmpty()){
            result.rejectValue("lastName","notFound","not found");
            return new ModelAndView("owners/findOwners");
        } else if(foundOwners.size() == 1){
            return new ModelAndView("redirect:/owners/" + foundOwners.stream().findFirst().get().getId());
        } else {
            return new ModelAndView("owners/ownersList").addObject("selections",foundOwners);
        }
    }

    @GetMapping("/new")
    public ModelAndView addOwnerForm(){
        return new ModelAndView("owners/createOrUpdateOwnerForm").addObject("owner",new Owner());
    }

    @GetMapping("/{ownerId:[1-9]\\d*}/edit")
    public ModelAndView updateOwnerForm(@PathVariable Long ownerId){
        Owner foundOwner = ownerService.findById(ownerId);
        if(foundOwner != null){
            return new ModelAndView("owners/createOrUpdateOwnerForm").addObject("owner",foundOwner);
        } else{
            return new ModelAndView("redirect:/owners/new");
        }
    }

    @PostMapping("/new")
    public ModelAndView addOrUpdateOwnerAction(@Validated Owner owner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/owners/new");
        }
        return new ModelAndView("redirect:/owners/" + ownerService.save(owner).getId());
    }

    @PostMapping("/{ownerId:[1-9]\\d*}/edit")
    public ModelAndView addOrUpdateOwnerAction(@PathVariable Long ownerId,
                                               @Validated Owner owner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("redirect:/owners/new");
        }
        owner.setId(ownerId);
        return new ModelAndView("redirect:/owners/" + ownerService.save(owner).getId());
    }

}
