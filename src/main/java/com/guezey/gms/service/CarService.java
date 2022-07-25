package com.guezey.gms.service;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.repo.GarageLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final GarageLogRepository logRepository;
    private final LogService logService;

    public CarService(GarageLogRepository logRepository, LogService logService) {
        this.logRepository = logRepository;
        this.logService = logService;
    }

    public Car getCarAtLot(int floor, int number, String block) {
        return logService.getLogAtLot(floor, number, block).getCar();
    }

    public List<Car> getCurrentCars() {
        List<Car> cars = new ArrayList<>();

        for( GarageLog log : logRepository.findByOutDateIsNull()) {
            cars.add(log.getCar());
        }
        return cars;
    }

    public List<Car> getCurrentCarsByFloor(int floor) {
        List<Car> cars = new ArrayList<>();
        logService.getCurrentLogsByFloor(floor).forEach(garageLog -> cars.add(garageLog.getCar()));
        return cars;
    }
}