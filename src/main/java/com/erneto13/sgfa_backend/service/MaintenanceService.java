package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.MaintenanceModel;
import com.erneto13.sgfa_backend.model.VehicleModel;

import java.util.List;

public interface MaintenanceService {
    List<MaintenanceModel> getAllMaintenanceRecords();
    MaintenanceModel createMaintenanceRecord(MaintenanceModel maintenanceRecord);
    MaintenanceModel updateMaintenanceStatus(Long id, MaintenanceModel.MaintenanceStatus status);
    List<MaintenanceModel> getMaintenanceRecordsByVehicle(Long vehicleId);
}