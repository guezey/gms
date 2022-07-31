package com.guezey.gms.service;

import com.guezey.gms.model.GarageLog;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class LogService {
    private final GarageLogRepository logRepository;
    private final ParkingLotRepository lotRepository;
    private final CarRepository carRepository;

    public LogService(GarageLogRepository logRepository, ParkingLotRepository lotRepository, CarRepository carRepository) {
        this.logRepository = logRepository;
        this.lotRepository = lotRepository;
        this.carRepository = carRepository;
    }

    public List<GarageLog> getLogsByDateString(String date) {
        Timestamp ts = Timestamp.from(Instant.parse(date + "T00:00:00.00Z"));
        return logRepository.findByInDateIsAfterAndInDateIsBefore(ts, new Timestamp(ts.getTime()+86400000));
    }

    public GarageLog getLogAtLot(int floor, int number, String block) {
        return logRepository.findByLot(
                lotRepository.findByFloorAndNumberAndBlock(floor,number,block)
        );
    }

    public List<GarageLog> getCurrentLogsByFloor(int floor) {
        return logRepository.findByLot_FloorAndOutDateIsNull(floor);
    }

    public void createNewLog(String carId, String lotId, String inDate) {
        GarageLog newLog = new GarageLog();
        newLog.setCar(carRepository.findById(Integer.parseInt(carId)));
        newLog.setLot(lotRepository.findParkingLotById(Integer.parseInt(lotId)));
        newLog.setInDate(Timestamp.from(Instant.parse(inDate)));
        newLog.setOutDate(null);
        logRepository.save(newLog);
    }

    public boolean addOutDateToLog(String carPlate, String outDate) {
        if (verifyCarId(carPlate)) {
            GarageLog log = logRepository.findByCar_PlateAndOutDateIsNull(carPlate);
            if (log != null) {
                log.setOutDate(Timestamp.from(Instant.parse(outDate)));
                logRepository.save(log);
                return true;
            }
        }
        return false;
    }

    private boolean verifyCarId(String carId) {
        return carRepository.findById(Integer.parseInt(carId)) != null;
    }
}
