package com.guezey.gms.controller;

import com.guezey.gms.model.Car;
import com.guezey.gms.service.GarageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hi")
public class GarageRestController {
    private final GarageService garageService;

    public GarageRestController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return garageService.getCurrentCars();
    }
}
