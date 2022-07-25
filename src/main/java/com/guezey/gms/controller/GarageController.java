package com.guezey.gms.controller;

import com.guezey.gms.service.CarService;
import com.guezey.gms.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars")
public class GarageController {
    private final CarService carService;

    public GarageController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getCurrentCars());
        return "cars";
    }
}
