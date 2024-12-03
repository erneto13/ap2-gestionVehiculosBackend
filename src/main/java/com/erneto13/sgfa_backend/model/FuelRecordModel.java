package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_records")
@Data
public class FuelRecordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelrecord_id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fuelDate;
    private String liters;
    private String cost;
    private String station;
    private String odometer;
    private String notes;
}

