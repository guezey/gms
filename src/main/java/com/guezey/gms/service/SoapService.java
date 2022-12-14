package com.guezey.gms.service;

import com.guezey.gms.endpoint.exception.ClientFaultException;
import com.guezey.gms.model.Car;
import com.guezey.gms.model.GarageLog;
import com.guezey.gms.model.Person;
import com.guezey.gms.repo.CarRepository;
import com.guezey.gms.repo.GarageLogRepository;
import com.guezey.gms.repo.ParkingLotRepository;
import com.guezey.gms.repo.PersonRepository;
import com.guezey.gms.xml.CarXml;
import com.guezey.gms.xml.GarageLogXml;
import com.guezey.gms.xml.ParkingLotXml;
import com.guezey.gms.xml.request.*;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Service
public class SoapService {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
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

    public ParkCarResponse parkCar(ParkCarRequest request) throws DatatypeConfigurationException, ClientFaultException {
        if (! request.isValid())
            throw new ClientFaultException("Invalid request");

        ParkCarResponse response = new ParkCarResponse();

        if (carRepository.findByPlate(request.getCarPlate()) != null) {
            if (logRepository.findByCar_PlateAndOutDateIsNull(request.getCarPlate()) == null) {
                GarageLog log = new GarageLog();
                log.setCar(carRepository.findByPlate(request.getCarPlate()));
                log.setLot(lotRepository.findByFloorAndNumberAndBlock(request.getParkingLot().getFloor().intValue(),
                                                                      request.getParkingLot().getNumber().intValue(),
                                                                      request.getParkingLot().getBlock()));
                log.setInDate(Timestamp.from(Instant.parse(request.getInDate().toString() + ".00Z")));
                log.setOutDate(null);
                logRepository.save(log);

                GarageLogXml responseLog = new GarageLogXml(log);
                String inDate = DATE_FORMAT.format(log.getInDate());
                responseLog.setInDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(inDate));
                response.setMessage("Car has been parked successfully.");
                response.setLog(responseLog);
            }
            else
                response.setMessage("Car with plate " + request.getCarPlate() + " is already in the garage.");
        }
        else
            response.setMessage(request.getCarPlate() + " cannot be not found in the database. Register the car and try again.");

        return response;
    }

    public RemoveCarResponse removeCar(RemoveCarRequest request) throws DatatypeConfigurationException, ClientFaultException {
        if (! request.isValid())
            throw new ClientFaultException("Invalid request");

        RemoveCarResponse response = new RemoveCarResponse();

        if (logRepository.findByCar_PlateAndOutDateIsNull(request.getPlate()) != null) {
            GarageLog log = logRepository.findByCar_PlateAndOutDateIsNull(request.getPlate());
            log.setOutDate(Timestamp.from(Instant.parse(request.getOutDate().toString() + ".00Z")));
            logRepository.save(log);

            GarageLogXml responseLog = new GarageLogXml(log);
            String inDate = DATE_FORMAT.format(log.getInDate());
            String outDate = DATE_FORMAT.format(log.getOutDate());
            responseLog.setInDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(inDate));
            responseLog.setOutDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(outDate));

            response.setMessage("The car is removed from the garage successfully.");
            response.setLog(responseLog);
        }
        else
            response.setMessage("A car with plate number " + request.getPlate() + " is not present in the garage");

        return response;
    }

    public RegisterCarResponse registerCar(RegisterCarRequest request) {
        RegisterCarResponse response = new RegisterCarResponse();

        if (carRepository.findByPlate(request.getCarToRegister().getPlate()) == null) {
            Person person = new Person();
            person.setFirstName(request.getCarToRegister().getOwner().getFirstname());
            person.setLastName(request.getCarToRegister().getOwner().getLastname());
            person.setEmail(request.getCarToRegister().getOwner().getEmail());
            person.setGender(request.getCarToRegister().getOwner().getGender());
            person.setPhone(request.getCarToRegister().getOwner().getPhone());
            personRepository.save(person);

            Car carToRegister = new Car();
            carToRegister.setMake(request.getCarToRegister().getMake());
            carToRegister.setModel(request.getCarToRegister().getModel());
            carToRegister.setYear(request.getCarToRegister().getYear());
            carToRegister.setPlate(request.getCarToRegister().getPlate());
            carToRegister.setOwner(personRepository.findFirstByOrderByIdDesc());
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

    public ListLogsResponse listLogs(ListLogsRequest request) throws DatatypeConfigurationException, ClientFaultException {
        if (request.getDate() == null || request.getDate().toString().length() < 10)
            throw new ClientFaultException("Invalid date format. Format should be 'yyyy-MM-dd'");

        ListLogsResponse response = new ListLogsResponse();
        Timestamp start = Timestamp.valueOf(request.getDate().toString() + " 00:00:00"),
                  end = new Timestamp(start.getTime() + 86400000);
        List<GarageLog> logs;

        switch (request.getType()) {
            case "IN":
                response.setMessage("Listing entry logs of date " + request.getDate().toString());
                logs = logRepository.findByInDateBetweenOrderByInDate(start, end);
                break;

            case "OUT":
                response.setMessage("Listing exit logs of date " + request.getDate().toString());
                logs = logRepository.findByOutDateBetweenOrderByInDate(start, end);
                break;

            case "BOTH":
                response.setMessage("Listing entry and exit logs of date " + request.getDate().toString());
                logs = logRepository.findByInDateBetweenAndOutDateBetweenOrderByInDate(start, end, start, end);
                break;

            case "EITHER":
                response.setMessage("Listing entry or exit logs of date " + request.getDate().toString());
                logs = logRepository.findByInDateBetweenOrOutDateBetweenOrderByInDate(start, end, start, end);
                break;

            default:
                throw new ClientFaultException("Invalid type. Type must be one of these: 'IN', 'OUT', 'BOTH', 'EITHER'");
        }

        for (GarageLog log : logs) {
            ListLogsResponse.Log responseLog = new ListLogsResponse.Log();
            String entry = DATE_FORMAT.format(log.getInDate());
            responseLog.setEntry(DatatypeFactory.newInstance().newXMLGregorianCalendar(entry));
            if (log.getOutDate() != null) {
                String exit = DATE_FORMAT.format(log.getOutDate());
                responseLog.setExit(DatatypeFactory.newInstance().newXMLGregorianCalendar(exit));
            }
            responseLog.setPlate(log.getCar().getPlate());
            responseLog.setLot(new ParkingLotXml(log.getLot()));
            response.getLog().add(responseLog);
        }

        return response;
    }

    public LogsByCarResponse logsByCar(LogsByCarRequest request) throws DatatypeConfigurationException {
        LogsByCarResponse response = new LogsByCarResponse();

        if (logRepository.findByCar_PlateOrderByInDate(request.getPlate()) != null) {
            for (GarageLog log : logRepository.findByCar_PlateOrderByInDate(request.getPlate())) {
                LogsByCarResponse.Log responseLog = new LogsByCarResponse.Log();
                String entry = DATE_FORMAT.format(log.getInDate());
                responseLog.setEntry(DatatypeFactory.newInstance().newXMLGregorianCalendar(entry));
                if (log.getOutDate() != null) {
                    String exit = DATE_FORMAT.format(log.getOutDate());
                    responseLog.setExit(DatatypeFactory.newInstance().newXMLGregorianCalendar(exit));
                }
                responseLog.setLot(new ParkingLotXml(log.getLot()));
                response.getLog().add(responseLog);
            }
            response.setMessage("Listing logs of car with plate number " + request.getPlate());
        }
        else
            response.setMessage("No logs found for car with plate number " + request.getPlate());

        return response;
    }
}
