package com.guezey.gms.util;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.Person;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.PersonRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final CarRepository carRepository;
    private final PersonRepository personRepository;

    public AppStartupEvent(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    public AppStartupEvent(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Car> cars = this.carRepository.findAll();
        Iterable<Person> people = this.personRepository.findAll();
        cars.forEach(System.out::println);
        people.forEach(System.out::println);
    }
}
