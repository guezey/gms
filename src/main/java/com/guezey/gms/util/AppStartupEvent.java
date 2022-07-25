package com.guezey.gms.util;

import com.guezey.gms.service.GarageService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
   private final GarageService garageService;

    public AppStartupEvent(GarageService garageService) {
        this.garageService = garageService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Iterable<Car> cars = this.carRepository.findAll();
//        Iterable<Person> people = this.personRepository.findAll();
//        Iterable<ParkingLot> lots = this.parkingLotRepository.findAll();
//        Iterable<GarageLog> logs = this.garageLogRepository.findAll();
//        cars.forEach(System.out::println);
//        people.forEach(System.out::println);
//        lots.forEach(System.out::println);
//        logs.forEach(System.out::println);

        System.out.println(garageService.getCarAtLot(2,12,"I"));
    }
}
