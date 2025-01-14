package com.erneto13.sgfa_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "completed_routes")
@Data
public class CompletedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "destination_location")
    private String destinationLocation;

    @Column(name = "destination_lat")
    private Double destinationLat;

    @Column(name = "destination_lng")
    private Double destinationLng;

    @Column(name = "origin_lat")
    private Double originLat;

    @Column(name = "origin_lng")
    private Double originLng;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

}