package com.insurance.insurancemanagementsystem.claim.entity;

import com.insurance.insurancemanagementsystem.common.enums.MediaType;
import com.insurance.insurancemanagementsystem.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "claim_assignment_history")
public class ClaimAssignmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    
    private LocalDateTime assignedAt;

//    private String assignedBy; // SYSTEM / ADMIN
}
