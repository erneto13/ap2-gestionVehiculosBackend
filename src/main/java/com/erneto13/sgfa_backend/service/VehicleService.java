package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleModel createVehicle(VehicleModel vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<VehicleModel> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public VehicleModel getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public VehicleModel updateVehicle(Long id, VehicleModel updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    updatedVehicle.setVehicle_id(id);
                    return vehicleRepository.save(updatedVehicle);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
