package com.guezey.gms.controller;

import com.guezey.gms.model.Car;
import com.guezey.gms.service.CarService;
import com.guezey.gms.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class GarageRestController {
    private final CarService carService;
    private final LogService logService;

    public GarageRestController(CarService carService, LogService logService) {
        this.carService = carService;
        this.logService = logService;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getCurrentCars();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void parkCar(String carId, String lotId, String inDate) {
        logService.createNewLog(carId, lotId, inDate);
    }
}
