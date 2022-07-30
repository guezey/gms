package com.guezey.gms.repo;

import com.guezey.gms.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
    ParkingLot findByFloorAndNumberAndBlock(int floor, int number, String block);
    ParkingLot findParkingLotById(int id);
}
