package com.guezey.gms.service;

import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.model.Person;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import com.guezey.gms.repo.PersonRepository;
import com.guezey.gms.xml.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Service
public class SoapService {
    private final CarRepository carRepository;
    private final ParkingLotRepository lotRepository;
    private final GarageLogRepository logRepository;
    private final PersonRepository personRepository;

    public SoapService(CarRepository carRepository, ParkingLotRepository lotRepository, GarageLogRepository logRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.lotRepository = lotRepository;
        this.logRepository = logRepository;
        this.personRepository = personRepository;
    }

    public ParkCarResponse parkCar(ParkCarRequest request) throws DatatypeConfigurationException {
        ParkCarResponse response = new ParkCarResponse();

        if (carRepository.findByPlate(request.getCarPlate()) != null) {
            GarageLog log = new GarageLog();
            log.setCar(carRepository.findByPlate(request.getCarPlate()));
            log.setLot(lotRepository.findByFloorAndNumberAndBlock(request.getParkingLot().getFloor().intValue(),
                    request.getParkingLot().getNumber().intValue(),
                    request.getParkingLot().getBlock()));
            log.setInDate(Timestamp.from(Instant.parse(request.getInDate().toString() + ".00Z")));
            log.setOutDate(null);
            logRepository.save(log);

            GarageLogXml responseLog = new GarageLogXml(log);
            String inDate = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss").format(log.getInDate());
            responseLog.setInDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(inDate));
            response.setMessage("Your car is parked successfully.");
            response.setLog(responseLog);
        }
        else {
            response.setMessage("Car is not found in the database. Register the car and try again.");
        }

        return response;
    }

    public RemoveCarResponse removeCar(RemoveCarRequest request) throws DatatypeConfigurationException {
        RemoveCarResponse response = new RemoveCarResponse();

        if (logRepository.findByCar_PlateAndOutDateIsNull(request.getPlate()) != null) {
            GarageLog log = logRepository.findByCar_PlateAndOutDateIsNull(request.getPlate());
            log.setOutDate(Timestamp.from(Instant.parse(request.getOutDate().toString() + ".00Z")));
            logRepository.save(log);

            GarageLogXml responseLog = new GarageLogXml(log);
            String inDate = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss").format(log.getInDate());
            responseLog.setInDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(inDate));
            responseLog.setOutDate(request.getOutDate());
            response.setMessage("The car is removed from the garage successfully.");
            response.setLog(responseLog);
        }
        else {
            response.setMessage("A car with plate number " + request.getPlate() + " is not present in the garage");
        }

        return response;
    }

    public RegisterCarResponse registerCar(RegisterCarRequest request) {
        RegisterCarResponse response = new RegisterCarResponse();
        if (carRepository.findByPlate(request.getCarToRegister().getPlate()) == null) {
            Car carToRegister = new Car();
            Person person = new Person();
            carToRegister.setMake(request.getCarToRegister().getMake());
            carToRegister.setModel(request.getCarToRegister().getModel());
            carToRegister.setYear(request.getCarToRegister().getYear());
            carToRegister.setPlate(request.getCarToRegister().getPlate());

            person.setFirstName(request.getCarToRegister().getOwner().getFirstname());
            person.setLastName(request.getCarToRegister().getOwner().getLastname());
            person.setEmail(request.getCarToRegister().getOwner().getEmail());
            person.setGender(request.getCarToRegister().getOwner().getGender());
            person.setPhone(request.getCarToRegister().getOwner().getPhone());

            personRepository.save(person);
            carToRegister.setOwner(personRepository.findLast());

            carRepository.save(carToRegister);
            response.setMessage("Your car has been registered successfully.");
            response.setRegisteredCar(request.getCarToRegister());
        }
        else {
            response.setMessage("A car with the same plate number already exists in the database.");
            response.setRegisteredCar(new CarXml(carRepository.findByPlate(request.getCarToRegister().getPlate())));
        }

        return response;
    }
}
