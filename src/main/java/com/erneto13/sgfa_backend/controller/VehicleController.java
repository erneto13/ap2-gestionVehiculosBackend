package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<VehicleModel>> list() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @PostMapping("/new-vehicle")
    public ResponseEntity<VehicleModel> addVehicle(@RequestBody VehicleModel vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }
}
