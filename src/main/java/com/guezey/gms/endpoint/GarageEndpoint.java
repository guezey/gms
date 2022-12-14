package com.guezey.gms.endpoint;

import com.guezey.gms.endpoint.exception.ClientFaultException;
import com.guezey.gms.service.SoapService;
import com.guezey.gms.xml.request.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GarageEndpoint {
    private static final String NAMESPACE = "http://guezey.com/gms/xml/request";
    private final SoapService soapService;

    public GarageEndpoint(SoapService soapService) {
        this.soapService = soapService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "parkCarRequest")
    @ResponsePayload
    public ParkCarResponse parkCar(@RequestPayload ParkCarRequest request) throws DatatypeConfigurationException, ClientFaultException {
        return soapService.parkCar(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "removeCarRequest")
    @ResponsePayload
    public RemoveCarResponse removeCar(@RequestPayload RemoveCarRequest request) throws DatatypeConfigurationException, ClientFaultException {
        return soapService.removeCar(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "registerCarRequest")
    @ResponsePayload
    public RegisterCarResponse registerCar(@RequestPayload RegisterCarRequest request) {
        return soapService.registerCar(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "listLogsRequest")
    @ResponsePayload
    public ListLogsResponse listLogs(@RequestPayload ListLogsRequest request) throws DatatypeConfigurationException, ClientFaultException {
        return soapService.listLogs(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "logsByCarRequest")
    @ResponsePayload
    public LogsByCarResponse logsByCar(@RequestPayload LogsByCarRequest request) throws DatatypeConfigurationException, IllegalArgumentException {
        return soapService.logsByCar(request);
    }
}
