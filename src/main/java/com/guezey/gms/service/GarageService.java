package com.guezey.gms.service;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class GarageService {
    private final GarageLogRepository garageLogRepository;
    private final ParkingLotRepository parkingLotRepository;

    public GarageService(GarageLogRepository garageLogRepository, ParkingLotRepository parkingLotRepository) {
        this.garageLogRepository = garageLogRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<Car> getCurrentCars() {
        List<Car> cars = new ArrayList<>();
        for( GarageLog log : garageLogRepository.findByOutDateIsNull()) {
            cars.add(log.getCar());
        }

        return cars;
    }

    public List<GarageLog> getLogsByDateString(String date) {
        Timestamp ts = Timestamp.from(Instant.parse(date + "T00:00:00.00Z"));
        return garageLogRepository.findByInDateIsAfterAndInDateIsBefore(ts, new Timestamp(ts.getTime()+86400000));
    }

    public List<GarageLog> getCurrentLogsByFloor(int floor) {
        return garageLogRepository.findByLot_FloorAndOutDateIsNull(floor);
    }

    public Car getCarAtLot(int floor, int number, String block) {
        return garageLogRepository.findByLot(
                parkingLotRepository.findByFloorAndNumberAndBlock(floor,number,block)
        ).getCar();
    }
}
