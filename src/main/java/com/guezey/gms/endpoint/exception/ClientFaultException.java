package com.guezey.gms.endpoint.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class ClientFaultException extends Exception{

    public ClientFaultException(String message) {
        super(message);
    }
}
