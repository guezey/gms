package com.guezey.gms.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class GarageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "car_id")
    private int carId;
    @Column(name = "lot_id")
    private int lotId;
    @Column(name = "in_date")
    private Timestamp inDate;
    @Column(name = "out_date")
    private Timestamp outDate;

    public GarageLog() {
    }

    public GarageLog(int id, int carId, int lotId, Timestamp inDate, Timestamp outDate) {
        this.id = id;
        this.carId = carId;
        this.lotId = lotId;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public Timestamp getInDate() {
        return inDate;
    }

    public void setInDate(Timestamp inDate) {
        this.inDate = inDate;
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "carId = " + carId + ", " +
                "lotId = " + lotId + ", " +
                "inDate = " + inDate + ", " +
                "outDate = " + outDate + ")";
    }
}
