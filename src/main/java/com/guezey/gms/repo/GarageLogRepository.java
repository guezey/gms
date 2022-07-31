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
    GarageLog findFirstByCar_Model(String carModel);

}
