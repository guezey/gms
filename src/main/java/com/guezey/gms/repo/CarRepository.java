package com.guezey.gms.repo;

import com.guezey.gms.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    public List<Car> findCarById(int id);
}
