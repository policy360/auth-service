package com.policy360.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {

    private Long id;
    private String policyNumber;
    private String policyType;
    private Double premiumAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;      // ACTIVE, EXPIRED, CANCELLED
    private Long customerId;
}
