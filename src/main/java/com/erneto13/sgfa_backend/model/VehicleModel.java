package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Data
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
