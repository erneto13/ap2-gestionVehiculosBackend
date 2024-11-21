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
        String sql = "INSERT INTO vehicles (license_plate, make, model, status, type_vehicle, image_url, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                vehicle.getLicensePlate(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getStatus(),
                vehicle.getType_vehicle(),
                vehicle.getImageUrl(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }
}
