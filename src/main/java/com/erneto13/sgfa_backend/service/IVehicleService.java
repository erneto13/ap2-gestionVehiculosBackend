package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.VehicleModel;

import java.util.List;

public interface IVehicleService {
    List<VehicleModel> getAllVehicles();
    int pushVehicle(VehicleModel vehicleModel);
}