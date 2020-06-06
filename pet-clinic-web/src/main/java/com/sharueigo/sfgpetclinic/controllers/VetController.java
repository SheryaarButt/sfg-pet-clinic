package com.sharueigo.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {

    @RequestMapping({"","/index.html","/index"})
    public String listVets(){
        return "vets/index";
    }

}
