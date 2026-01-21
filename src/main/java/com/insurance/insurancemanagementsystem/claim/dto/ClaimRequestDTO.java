package com.insurance.insurancemanagementsystem.claim.dto;

import com.insurance.insurancemanagementsystem.common.enums.ClaimSpecialization;
import com.insurance.insurancemanagementsystem.common.enums.ClaimType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClaimRequestDTO {

    private Long insurance_id;

    private ClaimType claimType;

    private String description;

    private LocalDateTime dateTime;

    private String location;

    private MultipartFile[] files;
}