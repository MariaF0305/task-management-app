package com.nttdata.task_management_app.controllers;

import com.nttdata.task_management_app.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/all")
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerService.findAllOwners());
        return "list-all-owners";
    }
}
