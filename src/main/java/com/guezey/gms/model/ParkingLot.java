package com.guezey.gms.model;

import javax.persistence.*;

@Entity
@Table(name = "lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "block")
    private String block;
    @Column(name = "number")
    private Integer number;

    public ParkingLot() {
    }

    public ParkingLot(Integer id, Integer floor, String block, Integer number) {
        this.id = id;
        this.floor = floor;
        this.block = block;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "floor = " + floor + ", " +
                "block = " + block + ", " +
                "number = " + number + ")";
    }

}
