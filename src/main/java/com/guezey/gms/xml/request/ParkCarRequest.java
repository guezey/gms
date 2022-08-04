//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.31 at 08:03:18 PM TRT 
//


package com.guezey.gms.xml.request;

import com.guezey.gms.xml.ParkingLotXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carPlate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parkingLot" type="{http://guezey.com/gms/xml}lot"/&gt;
 *         &lt;element name="inDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "carPlate",
    "parkingLot",
    "inDate"
})
@XmlRootElement(name = "parkCarRequest")
public class ParkCarRequest {

    @XmlElement(required = true)
    protected String carPlate;
    @XmlElement(required = true)
    protected ParkingLotXml parkingLot;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inDate;

    /**
     * Gets the value of the carPlate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarPlate() {
        return carPlate;
    }

    /**
     * Sets the value of the carPlate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarPlate(String value) {
        this.carPlate = value;
    }

    /**
     * Gets the value of the parkingLot property.
     * 
     * @return
     *     possible object is
     *     {@link ParkingLotXml }
     *     
     */
    public ParkingLotXml getParkingLot() {
        return parkingLot;
    }

    /**
     * Sets the value of the parkingLot property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParkingLotXml }
     *     
     */
    public void setParkingLot(ParkingLotXml value) {
        this.parkingLot = value;
    }

    /**
     * Gets the value of the inDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInDate() {
        return inDate;
    }

    /**
     * Sets the value of the inDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInDate(XMLGregorianCalendar value) {
        this.inDate = value;
    }

    public boolean isValid() {
        return ! (this.getCarPlate() == null || this.getInDate() == null || this.getParkingLot().getBlock() == null ||
                    this.getParkingLot().getFloor() == null || this.getParkingLot().getNumber() == null);
    }

}
