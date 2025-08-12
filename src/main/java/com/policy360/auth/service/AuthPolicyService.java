package com.policy360.auth.service;

import com.policy360.auth.dto.Policy;
import com.policy360.auth.feign.PolicyServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthPolicyService {

    private final PolicyServiceClient policyServiceClient;

    public Policy createPolicy(Policy policy) {
        return policyServiceClient.createPolicy(policy);
    }

    /**
     * Create a default policy for a customer with pre-filled details
     */
    public Policy createDefaultPolicy(Long customerId) {
        Policy policy = Policy.builder()
                .policyNumber("P-" + System.currentTimeMillis())
                .policyType("HEALTH")
                .premiumAmount(1500.0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .status("ACTIVE")
                .customerId(customerId)
                .build();
        return policyServiceClient.createPolicy(policy);
    }

    /**
     * Get policy by ID
     */
    public Policy getPolicyById(Long id) {
        return policyServiceClient.getPolicyById(id);
    }

    /**
     * Get all policies with pagination
     */
    public List<Policy> getAllPolicies(int page, int size) {
        return policyServiceClient.getAllPolicies(page, size);
    }

    /**
     * Update a policy by ID
     */
    public Policy updatePolicy(Long id, Policy policy) {
        return policyServiceClient.updatePolicy(id, policy);
    }

    /**
     * Delete a policy by ID
     */
    public void deletePolicy(Long id) {
        policyServiceClient.deletePolicy(id);
    }

    /**
     * Get all policies for a specific customer
     */
    public List<Policy> getPoliciesByCustomerId(Long customerId) {
        return policyServiceClient.getPoliciesByCustomerId(customerId);
    }
}
