package com.digitalkey.auth_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String vehicleId;

    @Column(nullable = false)
    private String status;   // ACTIVE / REVOKED

    private LocalDateTime createdAt;

    public DigitalKey() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
}
