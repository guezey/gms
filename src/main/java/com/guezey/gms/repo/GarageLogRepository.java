package com.guezey.gms.repo;

import com.guezey.gms.model.GarageLog;
import com.guezey.gms.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface GarageLogRepository extends JpaRepository<GarageLog, Integer> {
    List<GarageLog> findByOutDateIsNull();
    List<GarageLog> findByInDateIsAfterAndInDateIsBefore(Timestamp t1, Timestamp t2);
    List<GarageLog> findByLot_FloorAndOutDateIsNull(int floor);
    GarageLog findByLot(ParkingLot lot);
    GarageLog findByCar_PlateAndOutDateIsNull(String carPlate);
    List<GarageLog> findByInDateBetween(Timestamp start, Timestamp end);
    List<GarageLog> findByOutDateBetween(Timestamp start, Timestamp end);
    List<GarageLog> findByInDateBetweenOrOutDateBetween(Timestamp startIn, Timestamp endIn, Timestamp startOut, Timestamp endOut);
    List<GarageLog> findByInDateBetweenAndOutDateBetween(Timestamp startIn, Timestamp endIn, Timestamp startOut, Timestamp endOut);
    List<GarageLog> findByInDateBetweenAndLot_Floor(Timestamp start, Timestamp end, int floor);
    List<GarageLog> findByOutDateBetweenAndLot_Floor(Timestamp start, Timestamp end, int floor);
    List<GarageLog> findByInDateBetweenOrOutDateBetweenAndLot_Floor(Timestamp startIn, Timestamp endIn, Timestamp startOut, Timestamp endOut, int floor);
    List<GarageLog> findByInDateBetweenAndOutDateBetweenAndLot_Floor(Timestamp startIn, Timestamp endIn, Timestamp startOut, Timestamp endOut, int floor);
}
