package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.IssueModel;
import com.erneto13.sgfa_backend.model.VehicleModel;
import com.erneto13.sgfa_backend.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1")
public class VehicleController {

    @Autowired
    IVehicleService iVehicleService;

    @GetMapping("/vehicles/get-all")
    public ResponseEntity<?> list() {
        List<VehicleModel> vehicle = this.iVehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("/vehicles/new-vehicle")
    public ResponseEntity<Map<String, String>> addVehicle(@RequestBody VehicleModel vehicle) {
        int result = iVehicleService.pushVehicle(vehicle);

        if (result > 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vehículo creado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al crear el vehículo");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
