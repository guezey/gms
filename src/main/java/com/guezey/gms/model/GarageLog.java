package com.guezey.gms.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class GarageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id")
    private ParkingLot lot;

    @Column(name = "in_date", nullable = false)
    private Timestamp inDate;

    @Column(name = "out_date")
    private Timestamp outDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
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
        return "GarageLog{" +
                "id=" + id +
                ", car=" + car +
                ", parkingLotXml=" + lot +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                '}';
    }
}