package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_records")
@Data
public class MaintenanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "maintenance_type")
    private String maintenanceType;

    @Column(name = "maintenance_problem_type")
    private String maintenanceProblemType;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;

    private Double kilometers;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    private String mechanic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public enum MaintenanceStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }
}