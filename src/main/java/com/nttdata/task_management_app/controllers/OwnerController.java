package com.nttdata.task_management_app.controllers;

import com.nttdata.task_management_app.repositories.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping("/all")
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerRepository.findAll());

        return "list-all-owners";
    }
}
