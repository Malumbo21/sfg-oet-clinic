package com.osyrs.sfgpetclinic.controllers;

import com.osyrs.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @Index
    public String listVets(Model model) {
        model.addAttribute("vets",vetService.findAll());
        return "vets/index";
    }
}
