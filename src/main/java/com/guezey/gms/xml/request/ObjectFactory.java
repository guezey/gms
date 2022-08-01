//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.08.01 at 03:55:02 PM TRT 
//


package com.guezey.gms.xml.request;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.guezey.gms.xml.request package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.guezey.gms.xml.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListLogsResponse }
     * 
     */
    public ListLogsResponse createListLogsFullResponse() {
        return new ListLogsResponse();
    }

    /**
     * Create an instance of {@link ParkCarRequest }
     * 
     */
    public ParkCarRequest createParkCarRequest() {
        return new ParkCarRequest();
    }

    /**
     * Create an instance of {@link ParkCarResponse }
     * 
     */
    public ParkCarResponse createParkCarResponse() {
        return new ParkCarResponse();
    }

    /**
     * Create an instance of {@link RegisterCarRequest }
     * 
     */
    public RegisterCarRequest createRegisterCarRequest() {
        return new RegisterCarRequest();
    }

    /**
     * Create an instance of {@link RegisterCarResponse }
     * 
     */
    public RegisterCarResponse createRegisterCarResponse() {
        return new RegisterCarResponse();
    }

    /**
     * Create an instance of {@link RemoveCarRequest }
     * 
     */
    public RemoveCarRequest createRemoveCarRequest() {
        return new RemoveCarRequest();
    }

    /**
     * Create an instance of {@link RemoveCarResponse }
     * 
     */
    public RemoveCarResponse createRemoveCarResponse() {
        return new RemoveCarResponse();
    }

    /**
     * Create an instance of {@link ListLogsRequest }
     * 
     */
    public ListLogsRequest createListLogsRequest() {
        return new ListLogsRequest();
    }

    /**
     * Create an instance of {@link ListLogsResponse.Log }
     * 
     */
    public ListLogsResponse.Log createListLogsFullResponseLogMinimal() {
        return new ListLogsResponse.Log();
    }

}
