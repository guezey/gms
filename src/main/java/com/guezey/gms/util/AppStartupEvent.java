package com.guezey.gms.util;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.model.ParkingLot;
import com.guezey.gms.model.Person;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import com.guezey.gms.repo.PersonRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final CarRepository carRepository;
    private final PersonRepository personRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final GarageLogRepository garageLogRepository;

    public AppStartupEvent(CarRepository carRepository, PersonRepository personRepository, ParkingLotRepository parkingLotRepository, GarageLogRepository garageLogRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.garageLogRepository = garageLogRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Car> cars = this.carRepository.findAll();
        Iterable<Person> people = this.personRepository.findAll();
        Iterable<ParkingLot> lots = this.parkingLotRepository.findAll();
        Iterable<GarageLog> logs = this.garageLogRepository.findAll();
        cars.forEach(System.out::println);
        people.forEach(System.out::println);
        lots.forEach(System.out::println);
        logs.forEach(System.out::println);
    }
}
