package com.guezey.gms;

import com.guezey.gms.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GmsApplication{
    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(GmsApplication.class, args);
    }

}
