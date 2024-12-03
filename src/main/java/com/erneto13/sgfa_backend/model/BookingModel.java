package com.erneto13.sgfa_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookings_id;

    @Column(name = "vehicle_id")
    private Long vehicle_id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private VehicleModel vehicle;

    @Column(name = "driver_id")
    private Long driverId;

    @ManyToOne
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private DriverModel driver;

    @Column(name = "client_id")
    private Long contact_id;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ContactModel contact;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "destination_location", nullable = false)
    private String destination_location;

    @Column(name = "origin_lat", nullable = false)
    private double origin_lat;

    @Column(name = "origin_lng", nullable = false)
    private double origin_lng;

    @Column(name = "destination_lat", nullable = false)
    private double destination_lat;

    @Column(name = "destination_lng", nullable = false)
    private double destination_lng;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @Column(name = "notes", nullable = false)
    private String notes;
}
