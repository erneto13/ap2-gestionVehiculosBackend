package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.BookingModel;
import com.erneto13.sgfa_backend.model.FuelRecordModel;
import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.service.FuelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fuel-records")
public class FuelRecordController {

    private final FuelRecordService fuelRecordService;

    @Autowired
    public FuelRecordController(FuelRecordService fuelRecordService) {
        this.fuelRecordService = fuelRecordService;
    }

    @GetMapping("fuelrecord-list")
    public ResponseEntity<List<FuelRecordModel>> getAllBookings() {
        return new ResponseEntity<>(fuelRecordService.getAllFuelRecords(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FuelRecordModel> createFuelRecord(@RequestBody FuelRecordModel fuelRecordModel) {
        FuelRecordModel createdRecord = fuelRecordService.createFuelRecord(fuelRecordModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<FuelRecordModel>> getRecordsByVehicleId(@PathVariable Long vehicleId) {
        List<FuelRecordModel> records = fuelRecordService.getFuelRecordsByVehicleId(vehicleId);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }
}
