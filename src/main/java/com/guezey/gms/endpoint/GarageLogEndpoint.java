package com.guezey.gms.endpoint;

import com.guezey.gms.model.Car;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.xml.GetCarRequest;
import com.guezey.gms.xml.GetCarResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class GarageLogEndpoint {
    private static final String NAMESPACE = "http://guezey.com/gms/xml";
    private final CarRepository carRepository;

    public GarageLogEndpoint(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCarRequest")
    @ResponsePayload
    public GetCarResponse getCar(@RequestPayload GetCarRequest request) {
        GetCarResponse response = new GetCarResponse();
        List<Car> cars = carRepository.findByModel(request.getCarModel());
        cars.forEach(car -> {
            com.guezey.gms.xml.Car newCar = new com.guezey.gms.xml.Car();
            newCar.setMake(car.getMake());
            newCar.setModel(car.getModel());
            newCar.setYear(car.getYear());
            newCar.setPlate(car.getPlate());
            newCar.setOwner(new com.guezey.gms.xml.Car.Owner());
            newCar.getOwner().setFirstname(car.getOwner().getFirstName());
            newCar.getOwner().setLastname(car.getOwner().getLastName());
            newCar.getOwner().setPhone("1234567890");
            response.getCar().add(newCar);
        });

        return response;
    }
}
