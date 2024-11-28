package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<DriverModel> getAllDrivers() {
        return driverRepository.findAll();
    }

    public DriverModel saveDriver(DriverModel driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    public DriverModel updateDriver(Long id, DriverModel driver) {
        if (driverRepository.existsById(id)) {
            driver.setDriver_id(id);
            return driverRepository.save(driver);
        }
        return null;
    }
}