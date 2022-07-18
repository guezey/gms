package com.guezey.gms.model;

import javax.persistence.*;

@Entity
@Table(name = "lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "floor")
    private int floor;
    @Column(name = "block")
    private String block;
    @Column(name = "number")
    private int number;

    public ParkingLot() {
    }

    public ParkingLot(int id, int floor, String block, int number) {
        this.id = id;
        this.floor = floor;
        this.block = block;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
