package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/drivers")
@CrossOrigin(origins = "*")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("drivers-list")
    public ResponseEntity<List<DriverModel>> getAllDrivers() {
        List<DriverModel> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DriverModel> createDriver(@RequestBody DriverModel driver) {
        DriverModel newDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverModel> updateDriver(@PathVariable Long id, @RequestBody DriverModel driver) {
        DriverModel updatedDriver = driverService.updateDriver(id, driver);
        if (updatedDriver != null) {
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}