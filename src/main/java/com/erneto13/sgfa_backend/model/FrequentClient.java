package com.erneto13.sgfa_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "frequent_clients")
@Data
public class FrequentClient {

    @Id
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "booking_count", nullable = false)
    private Integer bookingCount;

}