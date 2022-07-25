package com.guezey.gms.controller;

import com.guezey.gms.service.GarageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class GarageController {
    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", garageService.getCurrentCars());
        return "cars";
    }
}
