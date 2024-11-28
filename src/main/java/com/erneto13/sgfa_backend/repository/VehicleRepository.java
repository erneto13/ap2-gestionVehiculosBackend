package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class VehicleRepository implements IVehicleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VehicleModel> getAllVehicles() {
        String sql = "SELECT * FROM vehicles";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VehicleModel.class));
    }

    @Override
    public int pushVehicle(VehicleModel vehicle) {
        String sql = "INSERT INTO vehicles (license_plate, brand, model, year, color, transmission_type, fuel_type, engine_type, type, status, registration_date, image_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                vehicle.getLicense_plate(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getTransmission_type(),
                vehicle.getFuel_type(),
                vehicle.getEngine_type(),
                vehicle.getType(),
                vehicle.getStatus(),
                vehicle.getRegistration_date(),
                vehicle.getImage_url()
        );
    }
}
