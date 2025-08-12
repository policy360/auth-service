package com.policy360.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    // Accessible only by CUSTOMER
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/policies")
    public String listMyPolicies() {
        return "List of customer policies";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/claims")
    public String listMyClaims() {
        return "List of customer claims";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/claim")
    public String fileClaim(@RequestBody String claimDetails) {
        return "Claim filed by customer";
    }
}
