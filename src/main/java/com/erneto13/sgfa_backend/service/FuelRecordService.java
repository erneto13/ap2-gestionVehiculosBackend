package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.model.FuelRecordModel;
import com.erneto13.sgfa_backend.repository.FuelRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelRecordService {

    private final FuelRecordRepository fuelRecordRepository;

    @Autowired
    public FuelRecordService(FuelRecordRepository fuelRecordRepository) {
        this.fuelRecordRepository = fuelRecordRepository;
    }

    public List<FuelRecordModel> getAllFuelRecords() {
        return fuelRecordRepository.findAll();
    }

    public FuelRecordModel createFuelRecord(FuelRecordModel fuelRecordModel) {
        return fuelRecordRepository.save(fuelRecordModel);
    }

    public List<FuelRecordModel> getFuelRecordsByVehicleId(Long vehicleId) {
        return fuelRecordRepository.findByVehicleId(vehicleId);
    }
}
