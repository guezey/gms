package com.guezey.gms.dto;

import java.io.Serializable;
import java.util.Objects;

public class CarDto implements Serializable {
    private final Integer id;
    private final String make;
    private final String model;
    private final String year;
    private final String plate;
    private final PersonDto owner;

    public CarDto(Integer id, String make, String model, String year, String plate, PersonDto owner) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.plate = plate;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getPlate() {
        return plate;
    }

    public PersonDto getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto entity = (CarDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.make, entity.make) &&
                Objects.equals(this.model, entity.model) &&
                Objects.equals(this.year, entity.year) &&
                Objects.equals(this.plate, entity.plate) &&
                Objects.equals(this.owner, entity.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, year, plate, owner);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "make = " + make + ", " +
                "model = " + model + ", " +
                "year = " + year + ", " +
                "plate = " + plate + ", " +
                "owner = " + owner + ")";
    }


}
