package com.guezey.gms.endpoint;

import com.guezey.gms.service.SoapService;
import com.guezey.gms.xml.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GarageLogEndpoint {
    private static final String NAMESPACE = "http://guezey.com/gms/xml";
    private final SoapService soapService;

    public GarageLogEndpoint(SoapService soapService) {
        this.soapService = soapService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "parkCarRequest")
    @ResponsePayload
    public ParkCarResponse parkCar(@RequestPayload ParkCarRequest request) throws DatatypeConfigurationException {
        return soapService.parkCar(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "removeCarRequest")
    @ResponsePayload
    public RemoveCarResponse removeCar(@RequestPayload RemoveCarRequest request) throws DatatypeConfigurationException {
        return soapService.removeCar(request);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "registerCarRequest")
    @ResponsePayload
    public RegisterCarResponse registerCar(@RequestPayload RegisterCarRequest request) {
        return soapService.registerCar(request);
    }
}
