package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository iVehicleRepository;

    @Override
    public List<VehicleModel> getAllVehicles() {
        return iVehicleRepository.getAllVehicles();
    }

    @Override
    public int pushVehicle(VehicleModel vehicleModel) {
        return iVehicleRepository.pushVehicle(vehicleModel);
    }
}
