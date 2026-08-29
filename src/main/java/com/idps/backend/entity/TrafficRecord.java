package com.idps.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrafficRecord {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sourceIp;

    @Column(nullable = false)
    private String protocolType;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String prediction;

    @Column(nullable = false)
    private boolean malicious;

    @Column(nullable = false)
    private double confidence;

    @Column(nullable = false)
    private boolean blockedAction;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Instant createdAt = Instant.now();
}
