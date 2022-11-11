package com.osyrs.sfgpetclinic.controllers;

import com.osyrs.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("vets.html")
    public String showVetList(Model model) {
        model.addAttribute("vets",vetService.findAll());
        return "vets/index";
    }
}
