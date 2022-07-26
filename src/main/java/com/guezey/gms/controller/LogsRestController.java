package com.guezey.gms.controller;

import com.guezey.gms.model.GarageLog;
import com.guezey.gms.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogsRestController {
    private final LogService logService;

    public LogsRestController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<GarageLog> getLogsAtDate(@RequestParam String date) {
        return logService.getLogsByDateString(date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void parkCar(@RequestParam("car") String carId,
                        @RequestParam("lot") String lotId,
                        @RequestParam("date") String inDate) {
        logService.createNewLog(carId, lotId, inDate);
    }

    @PatchMapping
    public void takeCarOut(String carId, String outDate) {
        if (! logService.addOutDateToLog(carId, outDate))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is not in the garage.");
    }
}
