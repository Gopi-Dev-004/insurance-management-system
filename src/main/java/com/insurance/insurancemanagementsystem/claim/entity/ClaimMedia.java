package com.insurance.insurancemanagementsystem.claim.entity;

import com.insurance.insurancemanagementsystem.common.enums.MediaType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "claim_media")
public class ClaimMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;

    @Column(name = "media_url", nullable = false)
    private String mediaUrl;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt = LocalDateTime.now();

    // getters & setters
}
