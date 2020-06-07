package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"","/index.html","/index"})
    public String listVets(Model model){

        model.addAttribute("vets",vetService.findAll());

        return "vets/index";
    }

}
