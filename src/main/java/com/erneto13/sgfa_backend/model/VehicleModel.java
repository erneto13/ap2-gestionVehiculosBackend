package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VehicleModel {
    private Long vehicle_id;
    private String license_plate;
    private String brand;
    private String model;
    private String year;
    private String color;

    private String transmission_type;
    private String fuel_type;
    private String engine_type;

    private String type;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registration_date;
    private String image_url;
}
