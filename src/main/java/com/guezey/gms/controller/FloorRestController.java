package com.guezey.gms.controller;

import com.guezey.gms.model.Car;
import com.guezey.gms.service.CarService;
import com.guezey.gms.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/floor/{floorNo}")
public class FloorRestController {
    private final LogService logService;
    private final CarService carService;

    public FloorRestController(LogService logService, CarService carService) {
        this.logService = logService;
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCurrentCarsOnFloor(@PathVariable String floorNo) {
        return carService.getCurrentCarsByFloor(Integer.parseInt(floorNo));
    }
}
