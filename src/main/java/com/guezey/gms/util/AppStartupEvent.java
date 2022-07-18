package com.guezey.gms.util;

import com.guezey.gms.model.Car;
import com.guezey.gms.repo.CarRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final CarRepository carRepository;

    public AppStartupEvent(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Car> cars = this.carRepository.findAll();
        cars.forEach(System.out::println);
    }
}
