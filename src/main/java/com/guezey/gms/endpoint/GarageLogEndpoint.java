package com.guezey.gms.endpoint;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import com.guezey.gms.service.SoapService;
import com.guezey.gms.xml.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Endpoint
public class GarageLogEndpoint {
    private static final String NAMESPACE = "http://guezey.com/gms/xml";
    private final SoapService soapService;

    public GarageLogEndpoint(SoapService soapService) {
        this.soapService = soapService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCarRequest")
    @ResponsePayload
    public GetCarResponse getCar(@RequestPayload GetCarRequest request) {
        GetCarResponse response = new GetCarResponse();
        List<Car> cars = carRepository.findByModel(request.getCarModel());
        cars.forEach(car -> {
            com.guezey.gms.xml.Car newCar = new com.guezey.gms.xml.Car(car);
            response.getCar().add(newCar);
        });

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "parkCarRequest")
    @ResponsePayload
    public ParkCarResponse parkCar(@RequestPayload ParkCarRequest request) {
        return soapService.parkCar(request);
    }
}
