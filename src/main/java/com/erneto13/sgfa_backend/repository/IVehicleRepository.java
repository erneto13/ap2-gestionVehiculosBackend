package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.VehicleModel;

import java.util.List;

public interface IVehicleRepository {
    List<VehicleModel> getAllVehicles();
    int pushVehicle(VehicleModel vehicle);
}
