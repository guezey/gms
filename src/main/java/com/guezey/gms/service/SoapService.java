package com.guezey.gms.service;

import com.guezey.gms.model.GarageLog;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import com.guezey.gms.xml.CarXml;
import com.guezey.gms.xml.ParkCarRequest;
import com.guezey.gms.xml.ParkCarResponse;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class SoapService {
    private final CarRepository carRepository;
    private final ParkingLotRepository lotRepository;
    private final GarageLogRepository logRepository;

    public SoapService(CarRepository carRepository, ParkingLotRepository lotRepository, GarageLogRepository logRepository) {
        this.carRepository = carRepository;
        this.lotRepository = lotRepository;
        this.logRepository = logRepository;
    }

    public ParkCarResponse parkCar(ParkCarRequest request) {
        ParkCarResponse response = new ParkCarResponse();
        GarageLog log = new GarageLog();
        log.setCar(carRepository.findByPlate(request.getCarPlate()));
        log.setLot(lotRepository.findByFloorAndNumberAndBlock(request.getParkingLot().getFloor().intValue(),
                request.getParkingLot().getNumber().intValue(),
                request.getParkingLot().getBlock()));
        log.setInDate(Timestamp.from(Instant.parse(request.getInDate().toString() + ".00Z")));
        log.setOutDate(null);
        logRepository.save(log);
        response.setParkedCar(new CarXml(log.getCar()));

        return response;
    }
}
