package com.idps.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BlockedIp {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ipAddress;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Instant blockedAt = Instant.now();

    @Column(nullable = false)
    private boolean active = true;

}
